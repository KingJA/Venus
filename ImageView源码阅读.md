* 在第三个构造方法里initImageView，获取属性
* 私有的变量m，私有静态的s
* 如果一定性使用的直接调用setAdjustViewBounds(a.getBoolean(R.styleable.ImageView_adjustViewBounds, false));
* 参数用小驼峰
* 版本特性sCompatAdjustViewBounds = targetSdkVersion <= Build.VERSION_CODES.JELLY_BEAN_MR1;











public enum ScaleType {
        MATRIX      (0),
        FIT_XY      (1),
        FIT_START   (2),
        FIT_CENTER  (3),
        FIT_END     (4),
        CENTER      (5),
        CENTER_CROP (6),
        CENTER_INSIDE (7);
        ScaleType(int ni) {
            nativeInt = ni;
        }
        final int nativeInt;
    }