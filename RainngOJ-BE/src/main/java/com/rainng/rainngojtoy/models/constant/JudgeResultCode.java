package com.rainng.rainngojtoy.models.constant;

public class JudgeResultCode {
    // 评测失败
    public static final int JUDGE_FAILED = -1;

    // 正确
    public static final int ACCEPTED = 0;

    // 答案错误
    public static final int WRONG_ANSWER = 1;

    // 编译错误
    public static final int COMPILE_ERROR = 2;

    // 运行错误
    public static final int RUNTIME_ERROR = 3;

    // 时间超限
    public static final int TIME_LIMIT_EXCEED = 4;

    // 内存超限
    public static final int MEMORY_LIMIT_EXCEED = 5;

    // 输出超限
    public static final int OUTPUT_LIMIT_EXCEED = 6;

    // 格式错误
    public static final int PRESENTATION_ERROR = 7;

    // 等待
    public static final int PENDING = 10;

    // 评测中
    public static final int JUDGING = 11;
}
