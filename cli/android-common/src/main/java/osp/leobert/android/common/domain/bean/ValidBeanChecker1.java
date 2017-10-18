package osp.leobert.android.common.domain.bean;

/**
 * <p><b>Package:</b> osp.leobert.android.common.domain.bean </p>
 * <p><b>Project:</b> cli </p>
 * <p><b>Classname:</b> ValidBeanChecker1 </p>
 * <p><b>Description:</b> not suggested to implement this interface to a Data-Class or POJO.
 *
 * when you have complex checking rules for a Data-Class, you can implement this interface on the
 * Data-Class to avoid mess code, but it cannot avoid a null check. all we wanted are encapsulating the logical and reusing.
 *
 * <em>strongly suggest to use {@link ValidBeanChecker2}</em>
 *
 * </p>
 * Created by leobert on 2017/10/18.
 */

public interface ValidBeanChecker1 {
    boolean VALID = true;
    boolean INVALID = false;

    /**
     * @return {@link #VALID} if the bean is valid,{@link #INVALID} otherwise
     */
    boolean isValid();
}
