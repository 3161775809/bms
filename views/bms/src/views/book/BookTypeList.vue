<template>
  <div class="hold-transition">
    <div class="content-header">
      <h1>
        图书管理<small>{{ this.$route.name }}</small>
      </h1>
      <el-breadcrumb separator-class="el-icon-arrow-right" class="breadcrumb">
        <el-breadcrumb-item :to="{ path: '/Home' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>图书管理</el-breadcrumb-item>
        <el-breadcrumb-item>{{ this.$route.name }}</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="app-container">
      <div class="box">
        <div class="filter-container">
          <el-input
            placeholder="图书类别名称"
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
        <el-table
          size="small"
          current-row-key="id"
          :data="dataList"
          stripe
          highlight-current-row
        >
          <el-table-column
            align="center"
            label="类别名"
            prop="type"
          ></el-table-column>
          <el-table-column
            prop="daynum"
            label="可借天数"
            align="center"
          ></el-table-column>
          <el-table-column
            prop="price"
            label="价格"
            align="center"
          ></el-table-column>
          <el-table-column label="操作" align="center">
            <template slot-scope="scope">
              <el-button
                type="primary"
                size="mini"
                @click="handleUpdate(scope.row)"
                >编辑</el-button
              >
              <el-button
                size="mini"
                type="danger"
                @click="handleDelete(scope.row)"
                >删除</el-button
              >
            </template>
          </el-table-column>
        </el-table>
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
        <!-- 新增标签弹层 -->
        <div class="add-form">
          <el-dialog title="新增图书类别" :visible.sync="dialogFormVisible">
            <template>
              <el-form
                :rules="rules"
                ref="dataAddForm"
                label-position="right"
                label-width="100px"
                :model="formData"
              >
                <el-row>
                  <el-form-item label="图书类别" prop="type">
                    <el-input v-model="formData.type" />
                  </el-form-item>
                </el-row>
                <el-row>
                  <el-form-item label="可借天数" prop="daynum">
                    <el-input v-model="formData.daynum" />
                  </el-form-item>
                </el-row>
                <el-row>
                  <el-form-item label="价格" prop="price">
                    <el-input v-model="formData.price" />
                  </el-form-item>
                </el-row>
              </el-form>
            </template>
            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogFormVisible = false">取消</el-button>
              <el-button type="primary" @click="handleAdd()">确定</el-button>
            </div>
          </el-dialog>
        </div>
        <div class="add-form">
          <el-dialog
            title="编辑图书类别"
            :visible.sync="dialogFormVisible4Edit"
          >
            <template>
              <el-form
                :rules="rules"
                ref="dataEditForm"
                label-position="right"
                label-width="100px"
                :model="formData"
              >
                <el-row>
                  <el-form-item label="图书类别" prop="type">
                    <el-input v-model="formData.type" />
                  </el-form-item>
                </el-row>
                <el-row>
                  <el-form-item label="可借天数" prop="daynum">
                    <el-input v-model="formData.daynum" />
                  </el-form-item>
                </el-row>
                <el-row>
                  <el-form-item label="价格" prop="price">
                    <el-input v-model="formData.price" />
                  </el-form-item>
                </el-row>
              </el-form>
            </template>
            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogFormVisible4Edit = false"
                >取消</el-button
              >
              <el-button type="primary" @click="handleEdit()">确定</el-button>
            </div>
          </el-dialog>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {
  addBookType,
  findBookTypePage,
  editBookType,
  findBookType,
  deleteBookType,
} from "@/api/book/booktype";
export default {
  name: "BookTypeList",
  data() {
    return {
      pagination: {
        //分页相关属性
        currentPage: 1,
        pageSize: 10,
        total: 100,
        queryString: "",
      },
      dataList: [], //列表数据
      formData: {}, //表单数据
      tableData: [], //新增和编辑表单中对应的图书类别项列表数据
      checkitemIds: [], //新增和编辑表单中图书类别项对应的复选框，基于双向绑定可以进行回显和数据提交
      dialogFormVisible: false, //控制添加窗口显示/隐藏
      dialogFormVisible4Edit: false, //控制编辑窗口显示/隐藏
      rules: {
        //校验规则
        type: [
          { required: true, message: "图书类别类别为必填项", trigger: "blur" },
        ],
        daynum: [
          { required: true, message: "可借天数为必填项", trigger: "blur" },
        ],
        price: [{ required: true, message: "价格为必填项", trigger: "blur" }],
      },
    };
  },
  created() {
    this.findPage();
  },
  methods: {
    //编辑
    handleEdit() {
      this.$refs["dataEditForm"].validate((valid) => {
        if (valid) {
          //表单数据校验通过，发送ajax请求将表单数据提交到后台
          editBookType(this.formData).then((res) => {
            this.dialogFormVisible4Edit = false;
            this.findPage();
          });
        } else {
          this.$message.error("表单数据校验失败");
          return false;
        }
      });
    },
    //添加
    handleAdd() {
      this.$refs["dataAddForm"].validate((valid) => {
        if (valid) {
          //表单数据校验通过，发送ajax请求将表单数据提交到后台
          addBookType(this.formData).then((res) => {
            his.dialogFormVisible = false;
            this.findPage();
          });
        }
      });
    },
    //分页查询
    findPage() {
      //获取所有图书类别数据
      findBookTypePage(this.pagination).then((res) => {
        this.dataList = res.data.data.records;
        this.pagination.total = res.data.data.total;
        this.$message.success(res.data.msg);
      });
    },
    // 重置表单
    // 重置表单
    resetForm() {
      this.formData = {};
      this.checkitemIds = [];
    },
    /// 弹出添加窗口
    handleCreate() {
      //设置显示添加窗口
      this.dialogFormVisible = true;
      //重置
      this.resetForm();
      //默认切换到第一个标签页（基本信息）
      //发送ajax请求查询所有图书类别项信息
    },
    //切换页码
    handleCurrentChange(currentPage) {
      this.pagination.currentPage = currentPage;
      this.findPage();
    },
    // 删除
    handleDelete(row) {
      this.$confirm(
        `确定删除图书类别名为${row.type}的图书类别吗?`,
        "删除图书类别",
        {
          type: "warning",
        }
      ).then(() => {
        deleteBookType(row.type).then((res) => {
          this.findPage();
        });
      });
    },
    handleUpdate(row) {
      this.resetForm();
      this.dialogFormVisible4Edit = true;
      findBookType({ type: row.type }).then((res) => {
        this.formData = res.data.data;
      });
    },
  },
};
</script>

<style lang="scss" scoped></style>
