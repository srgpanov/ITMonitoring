package com.srgpanov.itmonitoring.ui.screens.currency_list

import com.srgpanov.itmonitoring.R

class FlagContainer {
    companion object {
        private val flags = HashMap<String, Int>().apply {
            put("AUD", R.drawable.ic_aud)
            put("AZN", R.drawable.ic_azn)
            put("GBP", R.drawable.ic_gbp)
            put("AMD", R.drawable.ic_amd)
            put("BYN", R.drawable.ic_byn)
            put("BGN", R.drawable.ic_bgn)
            put("BRL", R.drawable.ic_brl)
            put("HUF", R.drawable.ic_huf)
            put("HKD", R.drawable.ic_hkd)
            put("DKK", R.drawable.ic_dkk)
            put("USD", R.drawable.ic_usd)
            put("EUR", R.drawable.ic_eur)
            put("INR", R.drawable.ic_inr)
            put("KZT", R.drawable.ic_kzt)
            put("CAD", R.drawable.ic_cad)
            put("KGS", R.drawable.ic_kgs)
            put("CNY", R.drawable.ic_cny)
            put("MDL", R.drawable.ic_mdl)
            put("NOK", R.drawable.ic_nok)
            put("PLN", R.drawable.ic_pln)
            put("RON", R.drawable.ic_ron)
            put("XDR", R.drawable.ic_xdr)
            put("SGD", R.drawable.ic_sgd)
            put("TJS", R.drawable.ic_tjs)
            put("TRY", R.drawable.ic_try)
            put("TMT", R.drawable.ic_tmt)
            put("UZS", R.drawable.ic_uzs)
            put("UAH", R.drawable.ic_uah)
            put("CZK", R.drawable.ic_czk)
            put("SEK", R.drawable.ic_sek)
            put("CHF", R.drawable.ic_chf)
            put("ZAR", R.drawable.ic_zar)
            put("KRW", R.drawable.ic_krw)
            put("JPY", R.drawable.ic_jpy)
            put("RUB", R.drawable.ic_rub)
        }

        fun getValuteFlag(charCode: String): Int {
            return flags[charCode] ?: R.drawable.ic_launcher_foreground
        }

    }
}