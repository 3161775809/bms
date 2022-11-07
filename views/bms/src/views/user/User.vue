<template>
  <div class="container">
    <div class="filter-container">
      <el-input
        placeholder="输入用户名搜索"
        v-model="pagination.queryString"
        style="width: 200px"
        class="filter-item"
        @keyup.enter.native="handleFilter"
      ></el-input>
      <el-button @click="findPage()" class="dalfBut">查询</el-button>
      <el-button type="primary" class="butT" @click="handleCreate()"
        >新建</el-button
      >
    </div>
    <el-table :data="tableData" border stripe>
      <el-table-column
        v-for="col in tableLabel"
        :prop="col.prop"
        :key="col.id"
        :label="col.label"
      >
      </el-table-column>
      <el-table-column prop="isadmin" label="权限等级">
        <template slot-scope="scope">
          {{ scope.row.isadmin ? "管理员" : "用户" }}
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="handleUpdate(scope.row)"
            >编辑</el-button
          >
          <el-button
            size="mini"
            type="danger"
            :disabled="scope.row.islend"
            @click="handleDelete(scope.row)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <!-- 编辑标签弹层 -->
    <div class="add-form">
      <el-dialog title="新增用户" :visible.sync="dialogFormVisible">
        <template>
          <el-form
            :rules="rules"
            ref="dataAddForm"
            label-position="right"
            label-width="100px"
            :model="formData"
          >
            <el-row>
              <el-col :span="12">
                <el-form-item label="用户名" prop="user">
                  <el-input v-model="formData.user" />
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="密码" prop="passwd">
                  <el-input v-model="formData.passwd" />
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="24">
                <el-form-item label="权限等级" prop="isadmin">
                  <el-select
                    v-model="formData.isadmin"
                    value-key="0"
                    placeholder="请选择权限等级"
                    clearable
                    filterable
                  >
                    <el-option label="用户" :value="0"> </el-option>
                    <el-option label="管理员" :value="1"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
          </el-form>
        </template>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible4Edit = false">取消</el-button>
          <el-button type="primary" @click="handleAdd()">确定</el-button>
        </div>
      </el-dialog>
    </div>
    <!-- 编辑标签弹层 -->
    <div class="edit-form">
      <el-dialog title="编辑用户" :visible.sync="dialogFormVisible4Edit">
        <template>
          <el-form
            :rules="rules"
            ref="dataEditForm"
            label-position="right"
            label-width="100px"
            :model="formData"
          >
            <el-row>
              <el-col :span="12">
                <el-form-item label="用户名" prop="user">
                  <el-input disabled v-model="formData.user" />
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="密码" prop="passwd">
                  <el-input v-model="formData.passwd" />
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="24">
                <el-form-item label="权限等级" prop="isadmin">
                  <el-select
                    v-model="formData.isadmin"
                    value-key="0"
                    placeholder="请选择权限等级"
                    clearable
                    filterable
                  >
                    <el-option label="用户" :value="0"> </el-option>
                    <el-option label="管理员" :value="1"> </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
          </el-form>
        </template>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible4Edit = false">取消</el-button>
          <el-button type="primary" @click="handleEdit()">确定</el-button>
        </div>
      </el-dialog>
      <div class="pagination-container">
        <el-pagination
          @current-change="handleCurrentChange"
          :current-page="pagination.currentPage"
          :page-size="pagination.pageSize"
          layout="total, prev, pager, next, jumper"
          :total="pagination.total"
        >
        </el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
import {
  findAllUser,
  deleteUser,
  editUser,
  register,
  findUserPage,
} from "@/api/user/user";
export default {
  name: "User",
  data() {
    return {
      pagination: {
        //分页相关属性
        currentPage: 1,
        pageSize: 10,
        total: 100,
        queryString: "",
      },
      tableData: [],
      tableLabel: [
        { id: 1, label: "用户名", prop: "user" },
        { id: 2, label: "密码", prop: "passwd" },
      ],
      dataList: [], //列表数据
      formData: {}, //表单数据
      tableData: [], //新增和编辑表单中对应的图书项列表数据
      checkitemIds: [], //新增和编辑表单中图书项对应的复选框，基于双向绑定可以进行回显和数据提交
      dialogFormVisible: false, //控制添加窗口显示/隐藏
      dialogFormVisible4Edit: false, //控制编辑窗口显示/隐藏
      rules: {
        //校验规则
        user: [{ required: true, message: "用户名为必填项", trigger: "blur" }],
        passwd: [
          { required: true, message: "为必填密码项", trigger: "blur" },
          {
            min: 6,
            max: 13,
            message: "密码长度在 6 到 13 个字符",
            trigger: "blur",
          },
        ],
        isadmin: [
          { required: true, message: "权限等级为必填项", trigger: "blur" },
        ],
      },
    };
  },
  created() {
    this.findPage();
  },
  methods: {
    findPage() {
      findUserPage(this.pagination).then((res) => {
        this.tableData = res.data.data.records;
        this.pagination.total = res.data.data.total;
      });
    },
    //编辑
    handleEdit() {
      this.$refs["dataEditForm"].validate((valid) => {
        if (valid) {
          //表单数据校验通过，发送ajax请求将表单数据提交到后台
          console.log(this.formData)
          editUser(this.formData).then((res) => {
            this.dialogFormVisible4Edit = false;
            this.$message.success(res.data.msg);
            //编辑成功，弹出成功提示
            this.findPage();
          });
        } else {
          this.$message.error("表单数据校验失败");
          return false;
        }
      });
    },
    // 重置表单
    resetForm() {
      this.formData = {};
      this.checkitemIds = [];
    },
    // 删除
    handleDelete(row) {
      if (row.user == "admin") {
        this.$message.warning("不能删除超级管理员");
        return false;
      }
      this.$confirm(`确定删除用户名为${row.user}的用户吗`, "删除用户", {
        type: "warning",
      }).then(() => {
        deleteUser(row).then((res) => {
          this.findPage();
          this.$message.success(res.data.msg);
        });
      }).catch(() => {});
    },
    handleUpdate(row) {
      this.resetForm();
      this.dialogFormVisible4Edit = true;
      this.formData = JSON.parse(JSON.stringify(row));
    },
    handleCreate(row) {
      this.resetForm();
      this.dialogFormVisible = true;
    },
    //新增用户
    handleAdd() {
      this.$refs["dataAddForm"].validate((valid) => {
        if (valid) {
          register(this.formData).then((res) => {
            this.$message.success(res.data.msg);
            this.dialogFormVisible = false;
            this.findPage();
          });
        } else {
          this.$message.error("表单数据校验失败");
          return false;
        }
      });
    },
    handleCurrentChange(currentPage) {},
  },
};
</script>

<style lang="scss" scoped></style>
