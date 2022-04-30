//
// Created by fengwei on 2022/4/30.
//

#ifndef ULTRABEAUTY_TRIANGLESAMPLE_H
#define ULTRABEAUTY_TRIANGLESAMPLE_H

#include "GLSampleBase.h"

class TriangleSample : public GLSampleBase {
public:
    TriangleSample();

    virtual ~TriangleSample();

    virtual void LoadImage(NativeImage *image);

    virtual void Init();

    virtual void Draw(int screenW, int screenH);

    virtual void Destroy();
};


#endif //ULTRABEAUTY_TRIANGLESAMPLE_H
