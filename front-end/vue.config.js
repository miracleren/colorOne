const path = require('path')
const resolve = dir => path.join(__dirname, dir)
const {defineConfig} = require('@vue/cli-service')
const defaultSettings = require('./src/settings.js')

const name = defaultSettings.title

module.exports = defineConfig({
    transpileDependencies: true,
    pluginOptions: {
        'style-resources-loader': {
            preProcessor: 'scss',
            patterns: []
        }
    },
    configureWebpack: {
        name:name,
        resolve: {
            alias: {
                '@': resolve('src')
            }
        }
    },
})

