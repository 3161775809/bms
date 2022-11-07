<template>
  <div class="hold-transitselectAllion">
    <div class="content-header">
      <h1>借阅者管理</h1>
      <el-breadcrumb separator-class="el-icon-arrow-right" class="breadcrumb">
        <el-breadcrumb-item :to="{ path: '/Home' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>{{ this.$route.name }}</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="app-container">
      <div class="box">
        <div class="filter-container">
          <el-input
            placeholder="借阅者名称"
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
            v-for="col in labelData"
            :prop="col.prop"
            :key="col.prop"
            :label="col.label"
          >
          </el-table-column>
          <el-table-column prop="sex" label="性别">
            <template slot-scope="scoped">
              <span>{{ scoped.row.sex == 1 ? "男" : "女" }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="loss" label="是否挂失">
            <template slot-scope="scoped">
              <span>{{ scoped.row.loss == 1 ? "已挂失" : "未挂失" }}</span>
            </template>
          </el-table-column>

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
                :disabled="scope.row.islend == 1"
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
          <el-dialog title="新增借阅者" :visible.sync="dialogFormVisible">
            <el-form
              ref="elForm"
              refString="dataAddForm"
              :rules="rules"
              :form-config="formConfig"
              :value="formData"
            >
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogFormVisible = false">取消</el-button>
              <el-button type="primary" @click="handleAdd()">确定</el-button>
            </div>
          </el-dialog>
        </div>
        <!-- 编辑标签弹层 -->
        <div class="eidt-form">
          <el-dialog title="编辑借阅者" :visible.sync="dialogFormVisible4Edit">
            <el-form
              ref="elForm"
              refString="dataEditForm"
              :rules="rules"
              :form-config="formConfig2"
              :value="formData"
            >
            </el-form>
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
  findBorrowerPage,
  insertBorrower,
  editBorrower,
  deleteBorrower,
} from "@/api/borrower/borrower";
import elForm from "@/components/elForm/elForm.vue";
export default {
  name: "Borrower",
  data() {
    return {
      pagination: {
        //分页相关属性
        currentPage: 1,
        pageSize: 5,
        total: 100,
        queryString: "",
      },
      dataList: [], //列表数据
      formData: {}, //表单数据
      tableData: [], //新增和编辑表单中对应的借阅者项列表数据
      dialogFormVisible: false, //控制添加窗口显示/隐藏
      dialogFormVisible4Edit: false, //控制编辑窗口显示/隐藏
      rules: {
        //校验规则
        address: [{ required: true, message: "地址为必填项", trigger: "blur" }],
        cardname: [
          { required: true, message: "用户名为必填项", trigger: "blur" },
        ],
        cardnum: [
          { required: true, message: "借阅卡号为必填项", trigger: "blur" },
        ],
        personnum: [
          { required: true, message: "身份证号为必填项", trigger: "blur" },
        ],
        phone: [{ required: true, message: "手机号为必填项", trigger: "blur" }],
        sex: [{ required: true, message: "性别为必填项", trigger: "blur" }],
        signdate: [
          { required: true, message: "办证日期为必填项", trigger: "blur" },
        ],
        type: [{ required: true, message: "身份为必填项", trigger: "blur" }],
        workplace: [
          { required: true, message: "工作地点为必填项", trigger: "blur" },
        ],
      },
      labelData: [
        {
          prop: "cardname",
          label: "借阅者名称",
        },
        {
          prop: "cardnum",
          label: "借阅卡号",
        },
        {
          prop: "type",
          label: "身份",
        },
        {
          prop: "personnum",
          label: "身份证号",
        },
        {
          prop: "phone",
          label: "手机号",
        },
        {
          prop: "address",
          label: "地址",
        },
        {
          prop: "borrowernum",
          label: "已借书数目",
        },
        {
          prop: "signdate",
          label: "办证日期",
        },
        {
          prop: "workplace",
          label: "工作地点",
        },
      ],
      //表单数据
      formConfig: {
        inline: true,
        size: "normal",
        labelWidth: "100",
        width: "180",
        formItemList: [
          {
            label: "用户名",
            prop: "cardname",
            type: "input",
            placeholder: "请输入用户名",
          },
          {
            label: "借阅卡号",
            prop: "cardnum",
            type: "input",
            placeholder: "请输入借阅卡号",
          },
          {
            label: "身份证号",
            prop: "personnum",
            type: "input",
            placeholder: "请输入身份证号",
          },
          {
            label: "手机号",
            prop: "phone",
            type: "input",
            placeholder: "请输入手机号",
          },
          {
            label: "性别",
            prop: "sex",
            type: "select",
            placeholder: "请输入性别",
            optList: [
              { label: "男", value: "0" },
              { label: "女", value: "1" },
            ],
            select: function () {},
          },
          {
            label: "地址",
            prop: "address",
            type: "input",
            placeholder: "请输入地址",
          },
          {
            label: "办证日期",
            prop: "signdate",
            type: "date",
            placeholder: "请选择办证日期",
          },
          {
            label: "身份",
            prop: "type",
            type: "input",
            placeholder: "请输入身份",
          },
          {
            label: "工作地点",
            prop: "workplace",
            type: "input",
            placeholder: "请输入工作地点",
          },
        ],
      },
      //表单数据
      formConfig2: {
        inline: true,
        size: "normal",
        labelWidth: "100",
        width: "180",
        formItemList: [
          {
            label: "用户名",
            prop: "cardname",
            type: "input",
            placeholder: "请输入用户名",
          },
          {
            label: "借阅卡号",
            prop: "cardnum",
            type: "input",
            placeholder: "请输入借阅卡号",
          },
          {
            label: "身份证号",
            prop: "personnum",
            type: "input",
            placeholder: "请输入身份证号",
          },
          {
            label: "手机号",
            prop: "phone",
            type: "input",
            placeholder: "请输入手机号",
          },
          {
            label: "性别",
            prop: "sex",
            type: "select",
            placeholder: "请选择性别",
            optList: [
              { label: "男", value: 0 },
              { label: "女", value: 1 },
            ],
            select: function () {},
          },
          {
            label: "是否挂失",
            prop: "loss",
            type: "select",
            placeholder: "请选择是否挂失",
            optList: [
              { label: "未挂失", value: 0 },
              { label: "挂失", value: 1 },
            ],
            select: () => {},
          },
          {
            label: "地址",
            prop: "address",
            type: "input",
            placeholder: "请输入地址",
          },
          {
            label: "办证日期",
            prop: "signdate",
            type: "date",
            placeholder: "请选择办证日期",
          },
          {
            label: "身份",
            prop: "type",
            type: "input",
            placeholder: "请输入身份",
          },
          {
            label: "工作地点",
            prop: "workplace",
            type: "input",
            placeholder: "请输入工作地点",
          },
        ],
      },
    };
  },
  components: {
    elForm,
  },
  //钩子函数，VUE对象初始化完成后自动执行
  created() {
    this.findPage();
  },
  methods: {
    //编辑
    handleEdit() {
      this.$refs["elForm"].$refs["dataEditForm"].validate((valid) => {
        if (valid) {
          //表单数据校验通过，发送ajax请求将表单数据提交到后台
          console.log(this.formData);
          editBorrower(this.formData).then((res) => {
            this.dialogFormVisible4Edit = false;
            //编辑成功，弹出成功提示
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
      this.$refs["elForm"].$refs["dataAddForm"].validate((valid) => {
        if (valid) {
          //表单数据校验通过，发送ajax请求将表单数据提交到后台
          console.log(this.formData);
          insertBorrower(this.formData).then((res) => {
            this.dialogFormVisible = false;
            this.$message.success(res.data.msg);
            this.findPage();
          });
        }
      });
    },
    //分页查询
    findPage() {
      findBorrowerPage(this.pagination).then((res) => {
        this.pagination.total = res.data.data.total;
        this.dataList = res.data.data.records;
        this.$message.success(res.data.msg);
      });
    },
    // 重置表单
    // 重置表单
    resetForm() {
      this.formData = { borrowernum: 0, loss: 0 };
      this.checkitemIds = [];
    },
    /// 弹出添加窗口
    handleCreate() {
      //设置显示添加窗口
      this.dialogFormVisible = true;
      //重置
      this.resetForm();
    },
    //切换页码
    handleCurrentChange(currentPage) {
      this.pagination.currentPage = currentPage;
      this.findPage();
    },
    // 删除
    handleDelete(row) {
      this.$confirm(`确定删除借阅者${row.cardname}的借阅卡吗`, "删除借阅卡", {
        type: "warning",
      }).then(() => {
        deleteBorrower(row).then((res) => {
          this.$message.success(res.data.msg);
          this.findPage();
        });
      });
    },
    handleUpdate(row) {
      this.resetForm();
      this.dialogFormVisible4Edit = true;
      this.formData = JSON.parse(JSON.stringify(row));
    },
  },
};
</script>

<style lang="scss" scoped></style>
