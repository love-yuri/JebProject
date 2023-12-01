<template>
  <el-table :data="tableData" style="width: 100%">
    <el-table-column prop="name" label="图书名字" width="180" />
    <el-table-column prop="price" label="图书价格" width="180" />
    <el-table-column align="right" v-if="username">
      <template #default="scope">
        <el-button size="small" @click="addUserBook(scope.$index)">添加订阅</el-button>
        <el-button size="small" type="danger" @click="delBook(scope.$index)">Delete</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script lang="ts" setup>
import { ref, onMounted, watch } from 'vue';
import { ElMessage } from 'element-plus';
import axios from 'axios';
const tableData = ref<
  {
    id: string;
    name: string;
  }[]
>([]);

const load = async () => {
  const { data } = await axios.get('http://localhost:8080/exp_9_war_exploded/hello-servlet');
  tableData.value = data;
};

const delBook = async (index: number) => {
  const { data } = await axios.post('http://localhost:8080/exp_9_war_exploded/deleteBook', {
    bookId: tableData.value[index].id
  });
  if (data) {
    load();
    ElMessage.success('删除成功');
  } else {
    ElMessage.error(data);
  }
};
const addUserBook = async (index: number) => {
  const { data } = await axios.post('http://localhost:8080/exp_9_war_exploded/user/book?action=add', {
    username: props.username,
    bookId: tableData.value[index].id
  });
  if (data.includes('成功')) {
    ElMessage.success(data);
    load();
  } else {
    ElMessage.error(data);
  }
};

const props = defineProps<{
  flush: boolean;
  username: string;
}>();

watch(
  () => props.flush,
  async () => {
    load();
  }
);

onMounted(async () => {
  load();
});
</script>
