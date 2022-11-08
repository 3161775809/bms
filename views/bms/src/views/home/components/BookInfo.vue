<template>
  <div class="bookinfo">
    <el-table
      :data="tableData"
      class="table"
      stripe
      highlight-current-row
      header-row-class-name="headerTitle"
    >
      <el-table-column
        v-for="label in tableLabel"
        :key="label.prop"
        :prop="label.prop"
        :label="label.label"
      >
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { findBorrowBookByCardNum, findBorrowPage } from "@/api/book/borrow";
export default {
  name: "BookInfo",
  data() {
    return {
      tableData: [],
      pagination: {
        //分页相关属性
        currentPage: 1,
        pageSize: 8,
        total: 100,
        queryString: '',
      },
      tableLabel: [
        {
          prop: "cardnum",
          label: "用户卡号",
        },
        {
          prop: "booknum",
          label: "图书号",
        },
        {
          prop: "blBookname",
          label: "图书名",
        },
        {
          prop: "borrowday",
          label: "借书日期",
        },
        {
          prop: "returnday",
          label: "还书日期",
        },
      ],
    };
  },
  created() {
    findBorrowPage(this.pagination).then(res => {
      this.tableData = res.data.data.records;
    })
  },
};
</script>

<style lang="scss" scoped>
.bookinfo {
  height: auto;
  background: white;

  .table {
    min-width: 400px;
  }
}
</style>
