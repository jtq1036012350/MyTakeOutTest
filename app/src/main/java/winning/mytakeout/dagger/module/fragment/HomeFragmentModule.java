package winning.mytakeout.dagger.module.fragment;

import dagger.Module;
import dagger.Provides;
import winning.mytakeout.presenter.fragment.HomeFragmentPresenter;

/**
 * Description: HomeFragmentModule
 * Author: Jiang
 * Date:   2017/1/19
 */
@Module
public class HomeFragmentModule {
    @Provides
    HomeFragmentPresenter provideHomeFragmentPresenter() {
        return new HomeFragmentPresenter();
    }
}
