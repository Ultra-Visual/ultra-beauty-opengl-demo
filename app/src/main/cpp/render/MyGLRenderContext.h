//
// Created by fengwei on 2022/4/29.
//

#ifndef ULTRABEAUTY_MYGLRENDERCONTEXT_H
#define ULTRABEAUTY_MYGLRENDERCONTEXT_H

#include <cstdint>
#include <GLES3/gl3.h>
#include "../sample/GLSampleBase.h"

class MyGLRenderContext {

    MyGLRenderContext();
    ~MyGLRenderContext();
public:
    void SetImageData(int format, int width, int height, uint8_t *pData);

    void OnSurfaceCreated();

    void OnSurfaceChanged(int width, int height);

    void OnDrawFrame();

    static MyGLRenderContext *GetInstance();

    static void DestroyInstance();

private:
    static MyGLRenderContext *m_pContext;
    GLSampleBase *m_pBeforeSample;
    GLSampleBase *m_pCurSample;
    int m_ScreenW;
    int m_ScreenH;
};


#endif //ULTRABEAUTY_MYGLRENDERCONTEXT_H
