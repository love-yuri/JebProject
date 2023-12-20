<!--
 * @Author: love-yuri yuri2078170658@gmail.com
 * @Date: 2023-11-30 09:22:10
 * @LastEditTime: 2023-12-07 08:11:42
 * @Description: 初始化
-->
<template>
  <div class="text-[40px]">
    <div>yuri is yes!</div>
    <A />
    <B />
  </div>
</template>
<script setup lang="ts">
import axios from 'axios';
import A from './a.vue';
import B from './b.vue';

axios.post('/');
</script>
