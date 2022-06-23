module.exports = {
  devServer: {
    proxy: {
      '^/api': {
        target: 'http://localhost:8080',
        ws: true,
        changeOrigin: true
      }
    }
  },
  outputDir: '../src/main/resources/static/dist', // webpack output 위치
  configureWebpack: (config) => {
    if (process.env.NODE_ENV === 'production') {
      // 배포 환경인 경우
      config.devtool = 'source-map';

      config.output.filename = "js/[name].js";
      config.output.chunkFilename = "js/[name].js";
      config.output.publicPath = "/";
    } else {
      // 개발 환경인 경우
      config.devtool = 'source-map';
    }
  },
  chainWebpack: config => {
    if (config.plugins.has("extract-css")) {
      const extractCSSPlugin = config.plugin("extract-css");
      extractCSSPlugin &&
      extractCSSPlugin.tap(() => [
        {
          // filename: assetsDir + "/[name].css",
          // chunkFilename: assetsDir + "/[name].css"
          filename: "css/[name].css",
          chunkFilename: "css/[name].css"
        }
      ]);
    }
  }
}