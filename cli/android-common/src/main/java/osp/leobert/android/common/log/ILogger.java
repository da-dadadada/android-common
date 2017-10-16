package osp.leobert.android.common.log;


import osp.leobert.android.common.log.impl.DefaultLogger;

/**
 * <p><b>Package:</b> osp.leobert.ijk.common </p>
 * <p><b>Project:</b> ijklib </p>
 * <p><b>Classname:</b> ILogger </p>
 * <p><b>Description:</b> logger </p>
 * Created by leobert on 2017/10/12.
 */

public interface ILogger {
//    boolean isShowLog = false;
//    boolean isShowStackTrace = false;

    String defaultTag = "[unknown]";

    ILogger logger = new DefaultLogger(defaultTag);

    void showLog(boolean isShowLog);

    void showStackTrace(boolean isShowStackTrace);

    void debug(String tag, String message);

    void info(String tag, String message);

    void warning(String tag, String message);

    void error(String tag, String message);

    void monitor(String message);

    boolean isMonitorMode();

    String getDefaultTag();
}
