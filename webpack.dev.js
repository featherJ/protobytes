'use strict';
const path = require('path')
const webpack = require('webpack');

module.exports = {
    target: 'node',
	mode: 'development',
	context: path.join(__dirname, 'src'),
	resolve: {
		extensions: ['.js', '.ts'],
		modules: [
			path.join(__dirname, './src'),
			"node_modules"
		]
	},
    devtool: 'source-map',
	optimization: {
		minimize: false
	},
	entry: {
		"./protobytes.js": ['./protobytes.ts']
	},
	output: {
		filename: '[name]',
		path: __dirname + '/out'
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
		new webpack.DefinePlugin({
			__IN_DEBUG__: JSON.stringify(true)
		})
	],
};