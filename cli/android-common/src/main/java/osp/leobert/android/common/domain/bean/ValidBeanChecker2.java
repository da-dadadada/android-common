package osp.leobert.android.common.domain.bean;

/**
 * <p><b>Package:</b> osp.leobert.android.common.domain.bean </p>
 * <p><b>Project:</b> cli </p>
 * <p><b>Classname:</b> ValidBeanChecker2 </p>
 * <p><b>Description:</b> <br>when you have complex checking rules for a Data-Class,
 * you can implement this interface on a checker class encapsulating the logical</br>
 *
 * Then relay on the checker class when you need.
 * <p>
 * Created by leobert on 2017/10/18.
 */

public interface ValidBeanChecker2<B> {
    boolean VALID = true;
    boolean INVALID = false;

    /**
     * @return {@link #VALID} if the bean is valid,{@link #INVALID} otherwise
     */
    boolean isValid(B bean);
}
