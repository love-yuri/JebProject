<!--
 * @Author: chen 梦断缘空 love-yuri jyh
 * @Date: 2023-11-30 19:20:54
 * @LastEditTime: 2023-11-30 20:16:10
 * @Description: 
-->
<template>
  <el-table :data="tableData" style="width: 100%">
    <el-table-column prop="name" label="图书名字" width="180" />
    <el-table-column prop="price" label="图书价格" width="180" />
    <el-table-column width="80">
      <div>添加借阅</div>
    </el-table-column>
    <el-table-column align="right">
      <template #default="scope">
        <el-button size="small" type="danger" @click="delBook(scope.$index)">归还</el-button>
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
  const { data } = await axios.post('http://localhost:8080/exp_9_war_exploded/user/book?action=list', {
    username: props.username
  });
  tableData.value = data;
};

const delBook = async (index: number) => {
  const { data } = await axios.post('http://localhost:8080/exp_9_war_exploded/user/book?action=user', {
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
