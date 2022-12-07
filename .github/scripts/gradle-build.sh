#!/bin/bash
# 构建脚本
# ---------------------

# 构建, 跳过测试, 指定构建目录

gradle build -x test -p caching

gradle build -x test -p core-utils

gradle build -x test -p core-web
