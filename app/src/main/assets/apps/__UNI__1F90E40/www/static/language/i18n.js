import Vue from 'vue';
import VueI18n from 'vue-i18n'

Vue.use(VueI18n);

// const i18n = new VueI18n({
//   locale: uni.getStorageSync('lang') == (undefined || "" || null) ? "de-DE" : uni.getStorageSync('lang'),
//   // locale: uni.getStorageSync('lang') || 'de-DE',
//   messages: {
//     'en-US':require("../../static/language/en-US.json"),
//     'de-DE':require("../../static/language/de-DE.json"),
// 	'zh-CN':require("../../static/language/zh-CN.json"),
//   },
// });
const i18n = new VueI18n({
	locale: uni.getStorageSync('lang') == (undefined || "" || null) ? "en-US" : uni.getStorageSync('lang'),
	messages: {
		'zh-CN': require("@/static/language/zh-CN.json"),
		// 'zh-HK': require("@/static/language/zh-HK.json"),
		'en-US': require("@/static/language/en-US.json"),
		'de-DE': require("@/static/language/de-DE.json"),
		'fr': require("@/static/language/fr.json"),
		// 'it-IT': require("@/static/language/it-IT.json"),
	}
})