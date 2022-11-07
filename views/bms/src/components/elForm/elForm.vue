<template>
  <el-form
    class="form"
    :inline="formConfig.inline"
    :model="value"
    :label-width="formConfig.labelWidth"
    :rules="rules"
    :ref="refString"
    :size="formConfig.size"
  >
    <slot name="formItem" />
    <el-form-item
      v-for="(item, index) in formConfig.formItemList"
      :key="index"
      :label="item.label"
      :prop="item.prop"
      :width="formConfig.width"
    >
      <el-input
        v-if="item.type == 'input'"
        v-model="value[item.prop]"
        :disabled="item.disabled"
        :clearable="true"
        :placeholder="item.placeholder"
      ></el-input>
      <el-select
        :clearable="true"
        v-else-if="item.type == 'select'"
        v-model="value[item.prop]"
        :disabled="item.disabled"
        :placeholder="item.placeholder"
        @change="item.select"
      >
        <el-option
          v-for="(optItem, index) in item.optList"
          :key="index"
          :label="optItem.label"
          :value="optItem.value"
        ></el-option>
      </el-select>
      <el-date-picker
        :value-format="item.dateFormate"
        v-else
        v-model="value[item.prop]"
        :type="item.type"
        :disabled="item.disabled"
        :clearable="true"
        :placeholder="item.label"
        format="yyyy-MM-dd HH:mm:ss"
        value-format="yyyy-MM-dd HH:mm:ss"
      ></el-date-picker>
    </el-form-item>
    <div class="searchBtn">
      <el-button-group>
        <el-button
          class="btn"
          v-for="(item, index) in formConfig.operate"
          :key="index"
          :size="item.size"
          :type="item.type"
          :icon="item.icon"
          @click="item.handleClick(value)"
          >{{ item.name }}
        </el-button>
      </el-button-group>
      <slot name="operate"></slot>
    </div>
  </el-form>
</template>
<script>
export default {
  components: {},
  props: {
    formConfig: {
      type: Object,
      required: true,
    },
    value: {
      type: Object,
      required: true,
    },
    rules: {
      type: Object,
    },
    refString: {
      type: String
    }
  },
  computed: {},
  methods: {
    setDefaultValue() {
      const formData = { ...this.value };
      // 设置默认值
      this.formConfig.formItemList.forEach(({ key, value }) => {
        if (formData[key] === undefined || formData[key] === null) {
          formData[key] = value;
        }
      });
    },
    _forceUpdate() {
      this.$forceUpdate();
    }
  },
  mounted() {
    this.setDefaultValue();
  },
};
</script>

<style lang="scss" scoped>
.form {
  min-width: 300px;
}

.searchBtn {
  .btn {
    margin-left: 20px;
  }
}
</style>
