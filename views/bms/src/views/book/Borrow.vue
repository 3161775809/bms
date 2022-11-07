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
            placeholder="借阅信息名称"
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
            label="借阅卡号"
            prop="cardnum"
          ></el-table-column>
          <el-table-column
            prop="booknum"
            label="图书号"
            align="center"
          ></el-table-column>
          <el-table-column
            prop="blCardname"
            label="借阅者姓名"
            align="center"
          ></el-table-column>
          <el-table-column
            prop="blBookname"
            label="书名"
            align="center"
          ></el-table-column>
          <el-table-column
            prop="borrowday"
            label="借出日期"
            align="center"
          ></el-table-column>
          <el-table-column
            prop="returnday"
            label="实际归还日期"
            align="center"
          ></el-table-column>
          <el-table-column
            prop="finemoney"
            label="罚款金额"
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
          <el-dialog title="新增借阅信息" :visible.sync="dialogFormVisible">
            <template>
              <el-tabs v-model="activeName" type="card" tab-position="top">
                <el-tab-pane name="借阅信息" label="借阅信息">
                  <el-form
                    ref="elForm"
                    refString="dataAddForm"
                    :rules="rules"
                    :form-config="formConfig"
                    :value="formData"
                  >
                  </el-form>
                </el-tab-pane>
                <el-tab-pane name="选择图书" label="选择图书">
                  <div class="filter-container">
                    <el-input
                      placeholder="图书名称"
                      v-model="searchData.queryString"
                      style="width: 200px"
                      class="filter-item"
                    ></el-input>

                    <el-button @click="findPage()" class="dalfBut"
                      >查询</el-button
                    >
                  </div>
                  <el-table
                    size="small"
                    current-row-key="id"
                    :data="bookList"
                    stripe
                    @current-change="currentChange"
                    highlight-current-row
                  >
                    <el-table-column type="index" width="50"> </el-table-column>
                    <el-table-column
                      prop="bookname"
                      label="图书名称"
                      align="center"
                    ></el-table-column>
                    <el-table-column
                      prop="author"
                      label="作者"
                      align="center"
                    ></el-table-column>
                    <el-table-column label="出版社" prop="press" align="center">
                    </el-table-column>
                    <el-table-column
                      prop="pressdate"
                      label="出版日期"
                      align="center"
                    ></el-table-column>
                    <el-table-column
                      prop="price"
                      label="价格"
                      align="center"
                    ></el-table-column>
                    <el-table-column
                      prop="type"
                      label="借阅信息"
                      align="center"
                    ></el-table-column>
                  </el-table>
                  <el-pagination
                    @current-change="handleCurrentChange2"
                    :current-page="searchData.currentPage"
                    :page-size="searchData.pageSize"
                    layout="total, prev, pager, next, jumper"
                    :total="searchData.total"
                  >
                  </el-pagination>
                </el-tab-pane>
              </el-tabs>
            </template>
            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogFormVisible = false">取消</el-button>
              <el-button type="primary" @click="handleAdd()">确定</el-button>
            </div>
          </el-dialog>
        </div>
        <!-- 新增标签弹层 -->
        <div class="edit-form">
          <el-dialog
            title="修改借阅信息"
            :visible.sync="dialogFormVisible4Edit"
          >
            <template>
              <el-tabs v-model="activeName" type="card" tab-position="top">
                <el-tab-pane name="借阅信息" label="借阅信息">
                  <el-form
                    ref="elForm2"
                    refString="dataEditForm"
                    :rules="rules"
                    :form-config="formConfig"
                    :value="formData"
                  >
                  </el-form>
                </el-tab-pane>
                <el-tab-pane name="选择图书" label="选择图书">
                  <div class="filter-container">
                    <el-input
                      placeholder="图书名称"
                      v-model="searchData.queryString"
                      style="width: 200px"
                      class="filter-item"
                    ></el-input>

                    <el-button @click="findPage()" class="dalfBut"
                      >查询</el-button
                    >
                  </div>
                  <el-table
                    size="small"
                    current-row-key="id"
                    :data="bookList"
                    stripe
                    @current-change="currentChange"
                    highlight-current-row
                  >
                    <el-table-column type="index" width="50"> </el-table-column>
                    <el-table-column
                      prop="bookname"
                      label="图书名称"
                      align="center"
                    ></el-table-column>
                    <el-table-column
                      prop="author"
                      label="作者"
                      align="center"
                    ></el-table-column>
                    <el-table-column label="出版社" prop="press" align="center">
                    </el-table-column>
                    <el-table-column
                      prop="pressdate"
                      label="出版日期"
                      align="center"
                    ></el-table-column>
                    <el-table-column
                      prop="price"
                      label="价格"
                      align="center"
                    ></el-table-column>
                    <el-table-column
                      prop="type"
                      label="借阅信息"
                      align="center"
                    ></el-table-column>
                  </el-table>
                  <el-pagination
                    @current-change="handleCurrentChange2"
                    :current-page="searchData.currentPage"
                    :page-size="searchData.pageSize"
                    layout="total, prev, pager, next, jumper"
                    :total="searchData.total"
                  >
                  </el-pagination>
                </el-tab-pane>
              </el-tabs>
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
import { findBorrowPage, deleteBorrow, editBorrow } from "@/api/book/borrow";
import { addBorrow } from "@/api/book/borrow";
import { findBookAll, editBook, selectBook } from "@/api/book/book";
import elForm from "@/components/elForm/elForm.vue";
import { findBookTypeAll } from "@/api/book/booktype";
import { selectAll } from "@/api/book/borrower";
export default {
  name: "Borrow",
  components: {
    elForm,
  },
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
      formData: {
        blCardname: "",
        cardnum: 1,
        booknum: 1,
        blBookname: "",
      }, //表单数据
      tableData: [], //新增和编辑表单中对应的借阅信息项列表数据
      checkitemIds: [], //新增和编辑表单中借阅信息项对应的复选框，基于双向绑定可以进行回显和数据提交
      dialogFormVisible: false, //控制添加窗口显示/隐藏
      dialogFormVisible4Edit: false, //控制编辑窗口显示/隐藏
      rules: {
        //校验规则
        cardnum: [
          { required: true, message: "借阅卡号为必填项", trigger: "blur" },
        ],
        blCardname: [
          { required: true, message: "借阅者姓名为必填项", trigger: "blur" },
        ],
        booknum: [
          { required: true, message: "图书号为必填项", trigger: "blur" },
        ],
        blBookname: [
          { required: true, message: "书名为必填项", trigger: "blur" },
        ],
        borrowday: [
          { required: true, message: "借出日期为必填项", trigger: "blur" },
        ],
        finemoney: [{ required: true, message: "罚款为必填项" }],
      },
      //表单数据
      formConfig: {
        inline: true,
        size: "normal",
        labelWidth: "100",
        width: "180",
        formItemList: [
          {
            label: "借阅卡号",
            prop: "cardnum",
            type: "input",
            disabled: true,
            placeholder: "请输入借阅卡号",
          },
          {
            label: "图书号",
            prop: "booknum",
            disabled: true,
            type: "input",
            placeholder: "请输入图书号",
          },
          {
            label: "借阅者姓名",
            prop: "blCardname",
            type: "select",
            optList: [],
            placeholder: "请选择借阅者姓名",
            select: (val) => {
              this.formData.blCardname = val.cardname;
              this.formData.cardnum = val.cardnum;
              this.$refs["elForm"]._forceUpdate();
            },
          },
          {
            label: "书名",
            prop: "blBookname",
            type: "input",
            disabled: true,
            placeholder: "请输入书名",
          },
          {
            label: "借出日期",
            prop: "borrowday",
            type: "date",
            placeholder: "请输入借出日期",
          },
        ],
      },
      searchData: {
        //分页相关属性
        currentPage: 1,
        pageSize: 10,
        total: 100,
        queryString: "",
      },
      bookList: [], //图书列表
      activeName: "借阅信息",
      bookTypeList: [],
    };
  },
  methods: {
    //编辑
    handleEdit() {
      this.$refs["elForm2"].$refs["dataEditForm"].validate((valid) => {
        if (valid) {
          //表单数据校验通过，发送ajax请求将表单数据提交到后台
          editBorrow(this.formData).then((res) => {
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
      this.$refs["elForm"].$refs["dataAddForm"].validate((valid) => {
        if (valid) {
          if (!this.formData.blBookname) {
            this.$message.warning("请选择要借的图书");
            this.activeName = "借阅图书";
            return false;
          }
          this.formData.finemoney = 0;
          const type = this.bookTypeList.filter(
            (item) => item.type == this.formData.type
          );
          this.formData.returnday =
            new Date(
              new Date(this.formData.borrowday).getTime() +
                type[0].daynum * 3600 * 24 * 1000
            ).toLocaleDateString() + " 00:00:00";
          this.formData.returnday = this.formData.returnday.replace(
            new RegExp("/", "g"),
            "-"
          );
          //表单数据校验通过，发送ajax请求将表单数据提交到后台
          delete this.formData.type;
          //获取对应的图书数据
          selectBook({
            booknum: this.formData.booknum,
            bookname: this.formData.blBookname,
          }).then((res) => {
            let book = res.data.data;
            //增加借阅信息
            addBorrow(this.formData).then((res) => {
              this.findPage();
              this.dialogFormVisible = false;
            });
            book.islend = 1;
            //修改图书状态为借出
            editBook(book).then((res) => {});
          });
        }
      });
    },
    //分页查询
    findPage() {
      //获取所有借阅信息数据
      findBorrowPage(this.pagination).then((res) => {
        this.dataList = res.data.data.records;
        this.pagination.total = res.data.data.total;
        this.$message.success(res.data.msg);
      });
      //获取所有图书数据
      findBookAll(this.searchData).then((res) => {
        this.searchData.total = res.data.total;
        //获取图书数据成功
        this.bookList = res.data.data.records.filter(
          (item) => item.islend != 1
        );
      });
      selectAll().then((res) => {
        res.data.data.forEach((item) => {
          let labelObject = {
            label: item.cardname,
            value: {
              cardname: item.cardname,
              cardnum: item.cardnum,
            },
          };
          this.$nextTick(() => {
            this.formConfig.formItemList[2].optList.push(labelObject);
          });
        });
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
      this.formData.cardnum = this.$store.getters.User.cardnum;
      this.formData.blCardname = this.$store.getters.User.cardname;
    },
    //切换页码
    handleCurrentChange(currentPage) {
      this.pagination.currentPage = currentPage;
      this.findPage();
    },
    handleCurrentChange2(currentPage) {
      this.searchData.currentPage = currentPage;
      this.findPage();
    },
    // 删除
    handleDelete(row) {
      this.$confirm(
        `确定删除借阅信息名为${row.booknum}的借阅信息吗?`,
        "删除借阅信息",
        {
          type: "warning",
        }
      ).then(() => {
        //获取对应的图书数据 //删除借阅信息
        deleteBorrow(row.booknum).then((res) => {
          this.findPage();
        });
        selectBook({
          booknum: row.booknum,
          bookname: row.bookname,
        }).then((res) => {
          let book = res.data.data;
          book.islend = 0;
          //修改图书状态为未借出
          editBook(book).then((res) => {});
        });
      });
    },
    handleUpdate(row) {
      this.resetForm();
      this.dialogFormVisible4Edit = true;
      this.formData = JSON.parse(JSON.stringify(row));
    },
    currentChange(val) {
      if (val) {
        this.$set(this.formData, "booknum", val.booknum);
        this.$set(this.formData, "blBookname", val.bookname);
        this.formData.type = val.type;
        this.$message.success("已选中" + val.bookname);
      }
    },
  },
  created() {
    this.findPage();
    findBookTypeAll().then((res) => {
      this.bookTypeList = res.data.data;
    });
  },
};
</script>

<style lang="scss" scoped></style>
