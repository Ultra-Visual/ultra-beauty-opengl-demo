//
// Created by fengwei on 2022/4/30.
//

#ifndef ULTRABEAUTY_GLSAMPLEBASE_H
#define ULTRABEAUTY_GLSAMPLEBASE_H

#include <cstdlib>
#include <GLES3/gl3.h>
#include <ImageDef.h>


class GLSampleBase {
public:
    GLSampleBase() {
        m_ProgramObj = 0;
        m_VertexShader = 0;
        m_FragmentShader = 0;
        m_Width = 0;
        m_Height = 0;
    }

    virtual ~GLSampleBase() {

    }

    virtual void LoadImage(NativeImage *image) {};

    virtual void LoadMultiImageWithIndex(int index, NativeImage *image) {};

    virtual void LoadShortArrData(short *const pShortArr, int width, int height) {};

    virtual void UpdateTransformMatrix(float rotateX, float rotateY, float scaleX, float scaleY) {};

    virtual void SetTouchLocation(float x, float y) {};

    virtual void SetGravityXY(float x, float y) {};

    virtual void Init() = 0;

    virtual void Draw(int screenW, int screenH) = 0;

    virtual void Destroy() = 0;

protected:
    GLuint m_VertexShader;
    GLuint m_FragmentShader;
    GLuint m_ProgramObj;
    int m_Width;
    int m_Height;
};

#endif //ULTRABEAUTY_GLSAMPLEBASE_H
