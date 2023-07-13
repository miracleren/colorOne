const path = require('path')
const resolve = dir => path.join(__dirname, dir)
const {defineConfig} = require('@vue/cli-service')
const defaultSettings = require('./src/frame/settings')

const name = defaultSettings.title

module.exports = defineConfig({
    transpileDependencies: true,
    lintOnSave: false,
    pluginOptions: {
        'style-resources-loader': {
            preProcessor: 'scss',
            patterns: []
        }
    },
    // webpack-dev-server 相关配置
    devServer: {
        host: '127.0.0.1',
        port: '818',
        open: true,
        proxy: {
            '/api': {
                target: `http://127.0.0.1:8181`,
                changeOrigin: true,
                pathRewrite: {
                    '^/api': ''
                }
            }
        }
    },
    configureWebpack: {
        name: name,
        resolve: {
            alias: {
                '@': resolve('src')
            }
        }
    },
    chainWebpack: config => {
        /**
         * svg-sprite-loader
         */
        config.module
            .rule('svg')
            .exclude.add(resolve('src/assets/icons/svg'))
            .end();
        config.module
            .rule('icons')
            .test(/\.svg$/)
            .include.add(resolve('src/assets/icons/svg'))
            .end()
            .use('svg-sprite-loader')
            .loader('svg-sprite-loader')
            .options({
                symbolId: 'icon-[name]',
            })
            .end();
    }
})

