package com.bluewhale.sa.ui.register

import com.bluewhale.sa.data.source.Navigator
import com.bluewhale.sa.data.source.register.DRequestToken


class RegisterNavigator constructor(val navi: Navigator) {
    fun goRootFragment() {
        navi.goRootFragment()
    }

    fun goRegisterInfoFragment(marketingClause: Boolean) {
        navi.addFragment(RegisterInfoFragment.getInstance(marketingClause))
    }

    fun goRegisterSMSFragment(marketingClause: Boolean, requestToken: DRequestToken) {
        navi.addFragment(RegisterSMSFragment.getInstance(marketingClause, requestToken))
    }
}