package com.srgpanov.itmonitoring.other

import com.srgpanov.itmonitoring.R
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun Disposable.addInCompositeDisp(disposable: CompositeDisposable):Disposable{
    disposable.add(this)
    return this
}
fun getValuteFlag(charCode:String):Int{
    return when(charCode){
        "AUD"->   R.drawable.ic_aud
        "AZN"->   R.drawable.ic_azn
        "GBP"->   R.drawable.ic_gbp
        "AMD"->   R.drawable.ic_amd
        "BYN"->   R.drawable.ic_byn
        "BGN"->   R.drawable.ic_bgn
        "BRL"->   R.drawable.ic_brl
        "HUF"->   R.drawable.ic_huf
        "HKD"->   R.drawable.ic_hkd
        "DKK"->   R.drawable.ic_dkk
        "USD"->   R.drawable.ic_usd
        "EUR"->   R.drawable.ic_eur
        "INR"->   R.drawable.ic_inr
        "KZT"->   R.drawable.ic_kzt
        "CAD"->   R.drawable.ic_cad
        "KGS"->   R.drawable.ic_kgs
        "CNY"->   R.drawable.ic_cny
        "MDL"->   R.drawable.ic_mdl
        "NOK"->   R.drawable.ic_nok
        "PLN"->   R.drawable.ic_pln
        "RON"->   R.drawable.ic_ron
        "XDR"->   R.drawable.ic_xdr
        "SGD"->   R.drawable.ic_sgd
        "TJS"->   R.drawable.ic_tjs
        "TRY"->   R.drawable.ic_try
        "TMT"->   R.drawable.ic_tjs
        "UZS"->   R.drawable.ic_uzs
        "UAH"->   R.drawable.ic_uah
        "CZK"->   R.drawable.ic_czk
        "SEK"->   R.drawable.ic_sek
        "CHF"->   R.drawable.ic_chf
        "ZAR"->   R.drawable.ic_zar
        "KRW"->   R.drawable.ic_krw
        "JPY"->   R.drawable.ic_jpy

        else -> R.drawable.ic_launcher_foreground
    }

}
