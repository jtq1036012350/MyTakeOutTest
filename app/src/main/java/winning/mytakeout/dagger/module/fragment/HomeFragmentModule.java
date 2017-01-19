package winning.mytakeout.dagger.module.fragment;

import dagger.Module;
import dagger.Provides;
import winning.mytakeout.presenter.fragment.HomeFragmentPresenter;
import winning.mytakeout.ui.fragments.HomeFragment;

/**
 * Description: HomeFragmentModule
 * Author: Jiang
 * Date:   2017/1/19
 */
@Module
public class HomeFragmentModule {
    private HomeFragment homeFragment;

    public HomeFragmentModule(HomeFragment homeFragment) {
        this.homeFragment = homeFragment;
    }

    @Provides
    HomeFragmentPresenter provideHomeFragmentPresenter() {
        return new HomeFragmentPresenter(homeFragment);
    }
}
