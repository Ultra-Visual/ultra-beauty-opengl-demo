//
// Created by fengwei on 2022/4/29.
//

#include "MyGLRenderContext.h"
#include "ImageDef.h"
#include "../sample/TriangleSample.h"

MyGLRenderContext *MyGLRenderContext::m_pContext = nullptr;

void MyGLRenderContext::SetImageData(int format, int width, int height, uint8_t *pData) {
    NativeImage nativeImage;
    nativeImage.format = format;
    nativeImage.width = width;
    nativeImage.height = height;
    nativeImage.ppPlane[0] = pData;

    switch (format) {
        case IMAGE_FORMAT_NV12:
        case IMAGE_FORMAT_NV21:
            nativeImage.ppPlane[1] = nativeImage.ppPlane[0] + width * height;
            break;
        case IMAGE_FORMAT_I420:
            nativeImage.ppPlane[1] = nativeImage.ppPlane[0] + width * height;
            nativeImage.ppPlane[2] = nativeImage.ppPlane[1] + width * height / 4;
            break;
        default:
            break;
    }
    if (m_pCurSample) {
        m_pCurSample->LoadImage(&nativeImage);
    }

}

void MyGLRenderContext::OnSurfaceCreated() {
    glClearColor(1.0f, 1.0f, 0.5f, 1.0f);
}

void MyGLRenderContext::OnSurfaceChanged(int width, int height) {
    glViewport(0, 0, width, height);
    m_ScreenW = width;
    m_ScreenH = height;
}

void MyGLRenderContext::OnDrawFrame() {
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    if (m_pBeforeSample) {
        m_pBeforeSample->Destroy();
        delete m_pBeforeSample;
        m_pBeforeSample = nullptr;
    }
    if (m_pCurSample) {
        m_pCurSample->Init();
        m_pCurSample->Draw(m_ScreenW, m_ScreenH);
    }
}

MyGLRenderContext *MyGLRenderContext::GetInstance() {
    if (m_pContext == nullptr) {
        m_pContext = new MyGLRenderContext();
    }
    return m_pContext;
}

void MyGLRenderContext::DestroyInstance() {
    if (m_pContext) {
        delete m_pContext;
        m_pContext = nullptr;
    }
}

MyGLRenderContext::~MyGLRenderContext() {
    if (m_pCurSample) {
        delete m_pCurSample;
        m_pCurSample = nullptr;
    }

    if (m_pBeforeSample) {
        delete m_pBeforeSample;
        m_pBeforeSample = nullptr;
    }
}

MyGLRenderContext::MyGLRenderContext() {
    m_pCurSample = new TriangleSample();
    m_pBeforeSample = nullptr;
}
