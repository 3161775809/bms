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
            placeholder="图书名称"
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
            label="图书号"
            prop="booknum"
          ></el-table-column>
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
            label="图书类别"
            align="center"
          ></el-table-column>
          <el-table-column
            prop="site"
            label="存放位置"
            align="center"
          ></el-table-column>
          <el-table-column
            prop="regdate"
            label="入库日期"
            align="center"
          ></el-table-column>
          <el-table-column label="是否借出" align="center">
            <template slot-scope="scope">
              <i class="el-icon-time"></i>
              <span style="margin-left: 10px">{{
                scope.row.islend ? "已借出" : "未借出"
              }}</span>
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
          <el-dialog title="新增图书" :visible.sync="dialogFormVisible">
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
                    <el-form-item label="图书号" prop="booknum">
                      <el-input v-model="formData.booknum" />
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item label="图书名称" prop="bookname">
                      <el-input v-model="formData.bookname" />
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="12">
                    <el-form-item label="作者" prop="author">
                      <el-input v-model="formData.author" />
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item label="出版社" prop="press">
                      <el-input v-model="formData.press" />
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="12">
                    <el-form-item label="出版日期" prop="pressdate">
                      <el-date-picker
                        v-model="formData.pressdate"
                        type="date"
                        placeholder="选择日期"
                        format="yyyy-MM-dd HH:mm:ss"
                        value-format="yyyy-MM-dd HH:mm:ss"
                      >
                      </el-date-picker>
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item label="价格" prop="price">
                      <el-input type="number" v-model="formData.price" />
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="12">
                    <el-form-item label="图书种类" prop="type">
                      <el-select v-model="formData.type">
                        <el-option
                          v-for="a in booktypeList"
                          :key="a.booknum"
                          :label="a.type + ' (可借' + a.daynum + '天)'"
                          :value="a.type"
                        ></el-option>
                      </el-select>
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item label="存放位置" prop="site">
                      <el-input v-model="formData.site" />
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="12">
                    <el-form-item label="入库日期" prop="regdate">
                      <el-date-picker
                        v-model="formData.regdate"
                        type="date"
                        placeholder="选择日期"
                        format="yyyy-MM-dd HH:mm:ss"
                        value-format="yyyy-MM-dd HH:mm:ss"
                      >
                      </el-date-picker>
                    </el-form-item>
                  </el-col>
                </el-row>
              </el-form>
            </template>
            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogFormVisible = false">取消</el-button>
              <el-button type="primary" @click="handleAdd()">确定</el-button>
            </div>
          </el-dialog>
        </div>

        <!-- 编辑标签弹层 -->
        <div class="add-form">
          <el-dialog title="编辑图书" :visible.sync="dialogFormVisible4Edit">
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
                    <el-form-item label="图书号" prop="booknum">
                      <el-input v-model="formData.booknum" />
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item label="图书名称" prop="bookname">
                      <el-input v-model="formData.bookname" />
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="12">
                    <el-form-item label="作者" prop="author">
                      <el-input v-model="formData.author" />
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item label="出版社" prop="press">
                      <el-input v-model="formData.press" />
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="12">
                    <el-form-item label="出版日期" prop="pressdate">
                      <el-date-picker
                        v-model="formData.pressdate"
                        type="date"
                        placeholder="选择日期"
                        format="yyyy-MM-dd HH:mm:ss"
                        value-format="yyyy-MM-dd HH:mm:ss"
                      >
                      </el-date-picker>
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item label="价格" prop="price">
                      <el-input type="number" v-model="formData.price" />
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="12">
                    <el-form-item label="图书种类" prop="type">
                      <el-select v-model="formData.type">
                        <el-option
                          v-for="a in booktypeList"
                          :key="a.booknum"
                          :label="a.type + ' (可借' + a.daynum + '天)'"
                          :value="a.type"
                        ></el-option>
                      </el-select>
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item label="存放位置" prop="site">
                      <el-input v-model="formData.site" />
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="12">
                    <el-form-item label="入库日期" prop="regdate">
                      <el-date-picker
                        v-model="formData.regdate"
                        type="date"
                        placeholder="选择日期"
                        format="yyyy-MM-dd HH:mm:ss"
                        value-format="yyyy-MM-dd HH:mm:ss"
                      >
                      </el-date-picker>
                    </el-form-item>
                  </el-col>
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
  findBookAll,
  addBook,
  deleteBook,
  selectBook,
  editBook,
} from "@/api/book/book";
import { findBookTypeAll } from "@/api/book/booktype";
export default {
  name: "BookList",
  data() {
    return {
      pagination: {
        //分页相关属性
        currentPage: 1,
        pageSize: 15,
        total: 100,
        queryString: "",
      },
      dataList: [], //列表数据
      formData: {}, //表单数据
      tableData: [], //新增和编辑表单中对应的图书项列表数据
      checkitemIds: [], //新增和编辑表单中图书项对应的复选框，基于双向绑定可以进行回显和数据提交
      dialogFormVisible: false, //控制添加窗口显示/隐藏
      dialogFormVisible4Edit: false, //控制编辑窗口显示/隐藏
      rules: {
        //校验规则
        booknum: [
          { required: true, message: "图书号为必填项", trigger: "blur" },
        ],
        bookname: [
          { required: true, message: "图书名为必填项", trigger: "blur" },
        ],
        author: [{ required: true, message: "作者为必填项", trigger: "blur" }],
        press: [{ required: true, message: "出版社为必填项", trigger: "blur" }],
        pressdate: [
          { required: true, message: "出版日期为必填项", trigger: "blur" },
        ],
        price: [
          { required: true, message: "图书价格为必填项", trigger: "blur" },
        ],
        type: [
          { required: true, message: "图书种类为必填项", trigger: "blur" },
        ],
        site: [
          { required: true, message: "存放位置为必填项", trigger: "blur" },
        ],
        regdate: [
          { required: true, message: "入库日期为必填项", trigger: "blur" },
        ],
      },
      booktypeList: [], //图书种类数据
    };
  },
  //钩子函数，VUE对象初始化完成后自动执行
  created() {
    this.findPage();
  },
  methods: {
    //编辑
    handleEdit() {
      this.$refs["dataEditForm"].validate((valid) => {
        if (valid) {
          //表单数据校验通过，发送ajax请求将表单数据提交到后台
          editBook(this.formData)
            .then((res) => {
              this.dialogFormVisible4Edit = false;
              this.findPage();
            })
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
          this.formData.islend = 0;
          addBook(this.formData)
            .then((res) => {
              this.dialogFormVisible = false;
              this.findPage();
            })
        }
      });
    },
    //分页查询
    findPage() {
      //获取所有图书数据
      findBookAll(this.pagination).then((res) => {
        //图书数据项成功
        this.dataList = res.data.data.records;
        this.pagination.total = res.data.data.total;
        this.$message.success(res.data.msg);
      });
      //获取图书种类数据
      findBookTypeAll().then((res) => {
        this.booktypeList = res.data.data;
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
      //发送ajax请求查询所有图书项信息
    },
    //切换页码
    handleCurrentChange(currentPage) {
      this.pagination.currentPage = currentPage;
      this.findPage();
    },
    // 删除
    handleDelete(row) {
      this.$confirm(`确定删除图书号为${row.booknum}的图书吗`, "删除图书", {
        type: "warning",
      }).then(() => {
        deleteBook(row.booknum).then((res) => {
          this.findPage();
        });
      });
    },
    handleUpdate(row) {
      this.resetForm();
      this.dialogFormVisible4Edit = true;
      selectBook({ booknum: row.booknum, bookname: row.bookname }).then(
        (res) => {
          this.formData = res.data.data;
        }
      );
    },
  },
};
</script>

<style scoped></style>
