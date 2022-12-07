#!/bin/bash
# 构建脚本
# ---------------------

# 构建, 跳过测试, 指定构建目录

gradle build -x test -p caching

gradle build -x test -p core-utils

gradle build -x test -p core-web

gradle build -x test -p data-elasticseach

gradle build -x test -p data-h2

gradle build -x test -p data-jdbc

gradle build -x test -p data-jpa

gradle build -x test -p data-redis

gradle build -x test -p jackson

gradle build -x test -p junit

gradle build -x test -p logging-logback

gradle build -x test -p mail

gradle build -x test -p minio

gradle build -x test -p mybatis

gradle build -x test -p quartz

gradle build -x test -p rabbit-mq

gradle build -x test -p release

gradle build -x test -p security-token

gradle build -x test -p thymeleaf

gradle build -x test -p util-starter

gradle build -x test -p validation

gradle build -x test -p version

gradle build -x test -p web

gradle build -x test -p webflux