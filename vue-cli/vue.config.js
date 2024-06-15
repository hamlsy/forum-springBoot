const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  // build 경로 설정
  outputDir: "../src/main/resources/static",
  devServer: {
    // 프록시 설정
    proxy: 'http://localhost:8080'
  }
})