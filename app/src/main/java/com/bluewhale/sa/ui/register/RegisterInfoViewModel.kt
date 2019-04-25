package com.bluewhale.sa.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bluewhale.sa.constant.MobileProvider
import com.bluewhale.sa.data.source.register.DRequestToken
import com.bluewhale.sa.data.source.register.RegisterInfoDataSource
import com.bluewhale.sa.data.source.register.RegisterInfoRepository
import com.bluewhale.sa.ui.BaseViewModel


class RegisterInfoViewModel(
    val navigator: RegisterNavigator,
    val registerRepository: RegisterInfoRepository,
    val marketingClause: Boolean
) : BaseViewModel() {

    private val _items = MutableLiveData<RegisterInfoData>()
        .apply { value = RegisterInfoData("", "", "", "", MobileProvider.UNSELECTED) }
    val items: LiveData<RegisterInfoData>
        get() = _items

    fun setName(name: String) {
        items.value?.name = name
    }

    fun setPersonalCode1(personalCode1: String) {
        items.value?.personalCode1 = personalCode1

        _nextButton.value = items.value?.isInfoFilledUp()
    }

    fun setPersonalCode2(personalCode2: String) {
        items.value?.personalCode2 = personalCode2

        _nextButton.value = items.value?.isInfoFilledUp()
    }

    fun setPhone(phone: String) {
        items.value?.phone = phone

        _nextButton.value = items.value?.isInfoFilledUp()
    }

    fun setProvider(provider: MobileProvider) {
        items.value?.provider = provider

        _nextButton.value = items.value?.isInfoFilledUp()
    }

    fun requestSMS() {
        if (items.value?.isInfoFilledUp()!!) {
            registerRepository.requestSMS(
                items.value!!.personalCode1,
                items.value!!.personalCode2,
                items.value!!.name,
                items.value!!.provider.providerCode,
                items.value!!.phone,
                object : RegisterInfoDataSource.CompletableCallback {
                    override fun onComplete(requestToken: DRequestToken) {
                        navigator.goRegisterSMSFragment(marketingClause, requestToken)
                    }

                    override fun onError(message: Int) {
                        _errorPopup.apply { value = message }
                    }
                }
            )
        }
    }
}