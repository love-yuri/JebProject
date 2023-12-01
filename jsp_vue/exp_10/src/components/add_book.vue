<!--
 * @Author: chen 梦断缘空 love-yuri jyh
 * @Date: 2023-11-30 14:10:45
 * @LastEditTime: 2023-11-30 15:21:31
 * @Description: 
-->
<!--
 * @Author: chen 梦断缘空 love-yuri jyh
 * @Date: 2023-11-30 14:10:45
 * @LastEditTime: 2023-11-30 14:14:22
 * @Description: 
-->
<template>
  <div class="w-full flex flex-col bg-slate-200 items-center justify-center">
    <div class="mt-3">
      <label>
        ISBN:
        <input
          type="text"
          placeholder="请输入书的书号"
          v-model="ISBN"
          name="ISBN"
          class="bg-slate-200 border-double border-4 ml-6 border-indigo-600"
        />
      </label>
    </div>
    <div class="mt-3">
      <label>
        书名:
        <input
          type="text"
          v-model="name"
          placeholder="请输入书的名字"
          name="name"
          class="bg-slate-200 border-double border-4 ml-6 border-indigo-600"
        />
      </label>
    </div>
    <div class="mt-3">
      <label>
        单价:
        <input
          type="text"
          v-model="price"
          placeholder="请输入书的单价"
          name="price"
          class="bg-slate-200 border-double border-4 ml-6 border-indigo-600"
        />
      </label>
    </div>
    <button class="border-dotted border-2 p-1 mt-2 border-indigo-600" @click="click">保存</button>
  </div>
</template>
<script setup lang="ts">
import { ref } from 'vue';
import axios from 'axios';
const ISBN = ref('');
const name = ref('');
const price = ref('');
const click = async () => {
  const { data } = await axios.post('http://localhost:8080/exp_9_war_exploded/addBook', {
    ISBN: ISBN.value,
    name: name.value,
    price: price.value
  });
  console.log(data);
};
</script>
