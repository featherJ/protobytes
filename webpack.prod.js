'use strict';
const path = require('path')
const webpack = require('webpack');

const UglifyJsPlugin = require('uglifyjs-webpack-plugin');

module.exports = {
    target: 'node',
	mode:'production',
    context: path.join(__dirname, 'src'),
    resolve: {
        extensions: ['*', '.js', '.ts'],
        modules: [
            path.join(__dirname, './src'),
            "node_modules"
        ]
    },
    optimization: {
		minimize: true
	},
    entry: {
        "./protobytes.js": ['./protobytes.ts']
    },
    output: {
        filename: '[name]',
        path: __dirname+'/bin'
    },
    module: {
        rules: [
            {
				test: /\.ts(x?)$/,
				use: ['ts-loader'],
			}
        ]
    },
    plugins: [
        new webpack.BannerPlugin({ banner: "#!/usr/bin/env node", raw: true }),
		new webpack.DefinePlugin({
			__IN_DEBUG__: JSON.stringify(false)
		}),
		new UglifyJsPlugin({
			sourceMap:false,
			uglifyOptions: {
				compress: {
					drop_console: false,//保留输出
					drop_debugger: true
				},
				output:{
					comments:false
				}
			}
		})
    ],
};