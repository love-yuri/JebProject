/*
 * @Author: chen 梦断缘空 love-yuri jyh
 * @Date: 2023-11-30 12:50:08
 * @LastEditTime: 2023-11-30 22:18:15
 * @Description:
 */
import { fileURLToPath, URL } from 'node:url';

import { defineConfig } from 'vite';
import vue from '@vitejs/plugin-vue';

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  build: {
    chunkSizeWarningLimit: 10000 // 设置新的警告阈值，单位为字节
  },
  base: './'
});
