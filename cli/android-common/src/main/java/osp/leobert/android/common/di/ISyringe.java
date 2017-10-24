package osp.leobert.android.common.di;

/**
 * desc the function of Syringe, one that used to inject
 * sth. to a container
 * <p>
 * Created by leobert on 2017/9/18.
 */

public interface ISyringe {
    /**
     * @param self the container itself, members to be inject into have been annotated
     *             with one annotation called Autowired
     */
    void inject(Object self);
}
