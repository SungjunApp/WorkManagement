package com.bluewhale.sa.ui.trade

import com.bluewhale.sa.navigator.Navigator
import com.bluewhale.sa.ui.HomeFragment


class TradeNavigator constructor(val navi: Navigator) {
    fun goRootFragment() {
        navi.goRootFragment()
    }

    fun goTradeDetailFragment(tradeId: String) {
        navi.addFragment(TradeDetailFragment.getInstance(tradeId))
    }

    fun goHomeFragment() {
        navi.replaceFragment(HomeFragment.getInstance())
    }
}