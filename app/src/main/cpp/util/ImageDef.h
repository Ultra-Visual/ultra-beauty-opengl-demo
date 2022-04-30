//
// Created by fengwei on 2022/4/30.
//

#ifndef ULTRABEAUTY_IMAGEDEF_H
#define ULTRABEAUTY_IMAGEDEF_H

#include <cstdint>

#define IMAGE_FORMAT_RGBA           0x01
#define IMAGE_FORMAT_NV21           0x02
#define IMAGE_FORMAT_NV12           0x03
#define IMAGE_FORMAT_I420           0x04
#define IMAGE_FORMAT_YUYV           0x05
#define IMAGE_FORMAT_GRAY           0x06
#define IMAGE_FORMAT_I444           0x07
#define IMAGE_FORMAT_P010           0x08

struct NativeImage {
    int width;
    int height;
    int format;
    uint8_t *ppPlane[3];

    NativeImage() {
        width = 0;
        height = 0;
        format = 0;
        ppPlane[0] = nullptr;
        ppPlane[1] = nullptr;
        ppPlane[2] = nullptr;
    }
};

#endif //ULTRABEAUTY_IMAGEDEF_H
