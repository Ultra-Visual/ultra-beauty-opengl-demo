//
// Created by fengwei on 2022/4/30.
//

#ifndef ULTRABEAUTY_LOGUTIL_H
#define ULTRABEAUTY_LOGUTIL_H

#include <android/log.h>

#define LOG_TAG "UltraBeauty"

#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)
#define LOGW(...) __android_log_print(ANDROID_LOG_WARN, LOG_TAG, __VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__)
#define LOGF(...) __android_log_print(ANDROID_LOG_FATAL, LOG_TAG, __VA_ARGS__)
#endif //ULTRABEAUTY_LOGUTIL_H
