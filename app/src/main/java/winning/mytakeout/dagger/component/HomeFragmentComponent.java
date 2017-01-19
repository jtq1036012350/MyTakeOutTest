package winning.mytakeout.dagger.component;

import dagger.Component;
import winning.mytakeout.dagger.module.fragment.HomeFragmentModule;
import winning.mytakeout.ui.fragments.HomeFragment;

/**
 * Description: 将创建好的业务对象设置给目标
 * Author: Jiang
 * Date:   2017/1/19
 */
@Component(modules = HomeFragmentModule.class)
public interface HomeFragmentComponent {
    void in(HomeFragment fragment);

}
