package com.abbas.bedWarws2023Gentrator.configuration;

import org.jetbrains.annotations.NotNull;
import com.tomkeuper.bedwars.api.language.Language;

public class Messages {

    @SuppressWarnings("WeakerAccess")
    public static final String PATH = "addons.rewards-generator.";

    // Messages
    public static final String XP_GAIN = PATH + "messages.xp-gain";
    public static final String LEVEL_UP = PATH + "messages.level-up";
    public static final String LUCKY_BOOST_EXPIRE = PATH + "lucky-boost.expire-message";

    // Boost Titles/Subtitles/Chat
    public static final String DOUBLE_TITLE = PATH + "lucky-boost.types.double-exp.title";
    public static final String DOUBLE_SUBTITLE = PATH + "lucky-boost.types.double-exp.subtitle";
    public static final String DOUBLE_CHAT = PATH + "lucky-boost.types.double-exp.chat-message";

    public static final String TRIPLE_TITLE = PATH + "lucky-boost.types.triple-exp.title";
    public static final String TRIPLE_SUBTITLE = PATH + "lucky-boost.types.triple-exp.subtitle";
    public static final String TRIPLE_CHAT = PATH + "lucky-boost.types.triple-exp.chat-message";

    public static final String MEGA_TITLE = PATH + "lucky-boost.types.mega-exp.title";
    public static final String MEGA_SUBTITLE = PATH + "lucky-boost.types.mega-exp.subtitle";
    public static final String MEGA_CHAT = PATH + "lucky-boost.types.mega-exp.chat-message";

    public static final String ULTIMATE_TITLE = PATH + "lucky-boost.types.ultimate-exp.title";
    public static final String ULTIMATE_SUBTITLE = PATH + "lucky-boost.types.ultimate-exp.subtitle";
    public static final String ULTIMATE_CHAT = PATH + "lucky-boost.types.ultimate-exp.chat-message";

    /**
     * Setup Default Messages and Settings for all requested languages
     */
    public static void setupMessages() {
        for (Language l : Language.getLanguages()) {
            String iso = l.getIso().toLowerCase();

            // Localization Logic
            switch (iso) {
                case "ro":
                    setupRomanian(l);
                    break;
                case "es":
                    setupSpanish(l);
                    break;
                case "pt":
                    setupPortuguese(l);
                    break;
                case "it":
                    setupItalian(l);
                    break;
                case "fr":
                    setupFrench(l);
                    break;
                case "ru":
                    setupRussian(l);
                    break;
                case "tr":
                    setupTurkish(l);
                    break;
                case "pl":
                    setupPolish(l);
                    break;
                case "zh":
                case "zh_cn":
                    setupChinese(l);
                    break;
                case "id":
                    setupIndonesian(l);
                    break;
                case "hi":
                    setupHindi(l);
                    break;
                case "fa":
                    setupPersian(l);
                    break;
                case "bd":
                case "bn":
                    setupBengali(l);
                    break;
                default:
                    setupEnglish(l);
                    break;
            }
        }
    }

    private static void setupEnglish(Language l) {
        addDefault(l, XP_GAIN, "&aYou gained &e{amount} Xp for {source}!");
        addDefault(l, LEVEL_UP, "&aYou leveled up! You are now level &e{level}&a!");
        addDefault(l, LUCKY_BOOST_EXPIRE, "&c&l✦ &6Your Lucky Boost has expired! &c&l✦");
        addDefault(l, DOUBLE_TITLE, "&6&lYOU GOT LUCKY!");
        addDefault(l, DOUBLE_SUBTITLE, "&bYou will receive &b&lDOUBLE EXP &bin this game!");
        addDefault(l, DOUBLE_CHAT, "&a&l✦ &6Lucky Boost! &a&l✦ &7You received &b&lDOUBLE EXP &7for &e{duration}s&7!");
        addDefault(l, TRIPLE_TITLE, "&d&lSUPER LUCKY!");
        addDefault(l, TRIPLE_SUBTITLE, "&bYou will receive &d&lTRIPLE EXP &bin this game!");
        addDefault(l, TRIPLE_CHAT, "&a&l✦ &6Lucky Boost! &a&l✦ &7You received &d&lTRIPLE EXP &7for &e{duration}s&7!");
        addDefault(l, MEGA_TITLE, "&6&lMEGA LUCKY!");
        addDefault(l, MEGA_SUBTITLE, "&bYou will receive &6&lMEGA EXP &bin this game!");
        addDefault(l, MEGA_CHAT, "&a&l✦ &6Lucky Boost! &a&l✦ &7You received &6&lMEGA EXP &7for &e{duration}s&7!");
        addDefault(l, ULTIMATE_TITLE, "&c&lULTIMATE LUCKY!");
        addDefault(l, ULTIMATE_SUBTITLE, "&bYou will receive &c&lULTIMATE EXP &bin this game!");
        addDefault(l, ULTIMATE_CHAT, "&a&l✦ &6Lucky Boost! &a&l✦ &7You received &c&lULTIMATE EXP &7for &e{duration}s&7!");
    }

    private static void setupRomanian(Language l) {
        addDefault(l, XP_GAIN, "&aAi primit &e{amount} Xp pentru {source}!");
        addDefault(l, LEVEL_UP, "&aAi avansat! Acum ești nivel &e{level}&a!");
        addDefault(l, LUCKY_BOOST_EXPIRE, "&c&l✦ &6Lucky Boost-ul tău a expirat! &c&l✦");
        addDefault(l, DOUBLE_TITLE, "&6&lAI AVUT NOROC!");
        addDefault(l, DOUBLE_SUBTITLE, "&bVei primi &b&lEXP DUBLU &bîn acest joc!");
        addDefault(l, DOUBLE_CHAT, "&a&l✦ &6Lucky Boost! &a&l✦ &7Ai primit &b&lEXP DUBLU &7pentru &e{duration}s&7!");
        addDefault(l, TRIPLE_TITLE, "&d&lNOROC SUPER!");
        addDefault(l, TRIPLE_SUBTITLE, "&bVei primi &d&lEXP TRIPLU &bîn acest joc!");
        addDefault(l, TRIPLE_CHAT, "&a&l✦ &6Lucky Boost! &a&l✦ &7Ai primit &d&lEXP TRIPLU &7pentru &e{duration}s&7!");
        addDefault(l, MEGA_TITLE, "&6&lNOROC MEGA!");
        addDefault(l, MEGA_SUBTITLE, "&bVei primi &6&lEXP MEGA &bîn acest joc!");
        addDefault(l, MEGA_CHAT, "&a&l✦ &6Lucky Boost! &a&l✦ &7Ai primit &6&lEXP MEGA &7pentru &e{duration}s&7!");
        addDefault(l, ULTIMATE_TITLE, "&c&lNOROC SUPREM!");
        addDefault(l, ULTIMATE_SUBTITLE, "&bVei primi &c&lEXP SUPREM &bîn acest joc!");
        addDefault(l, ULTIMATE_CHAT, "&a&l✦ &6Lucky Boost! &a&l✦ &7Ai primit &c&lEXP SUPREM &7pentru &e{duration}s&7!");
    }

    private static void setupSpanish(Language l) {
        addDefault(l, XP_GAIN, "&aGanaste &e{amount} Xp por {source}!");
        addDefault(l, LEVEL_UP, "&a¡Subiste de nivel! ¡Ahora eres nivel &e{level}&a!");
        addDefault(l, LUCKY_BOOST_EXPIRE, "&c&l✦ &6¡Tu Lucky Boost ha expirado! &c&l✦");
        addDefault(l, DOUBLE_TITLE, "&6&l¡TUVISTE SUERTE!");
        addDefault(l, DOUBLE_SUBTITLE, "&b¡Recibirás &b&lEXP DOBLE &ben este juego!");
        addDefault(l, DOUBLE_CHAT, "&a&l✦ &6¡Lucky Boost! &a&l✦ &7¡Recibiste &b&lEXP DOBLE &7por &e{duration}s&7!");
        addDefault(l, TRIPLE_TITLE, "&d&l¡SÚPER SUERTE!");
        addDefault(l, TRIPLE_SUBTITLE, "&b¡Recibirás &d&lEXP TRIPLE &ben este juego!");
        addDefault(l, TRIPLE_CHAT, "&a&l✦ &6¡Lucky Boost! &a&l✦ &7¡Recibiste &d&lEXP TRIPLE &7por &e{duration}s&7!");
        addDefault(l, MEGA_TITLE, "&6&l¡MEGA SUERTE!");
        addDefault(l, MEGA_SUBTITLE, "&b¡Recibirás &6&lMEGA EXP &ben este juego!");
        addDefault(l, MEGA_CHAT, "&a&l✦ &6¡Lucky Boost! &a&l✦ &7¡Recibiste &6&lMEGA EXP &7por &e{duration}s&7!");
        addDefault(l, ULTIMATE_TITLE, "&c&l¡SUERTE SUPREMA!");
        addDefault(l, ULTIMATE_SUBTITLE, "&b¡Recibirás &c&lEXP SUPREMA &ben este juego!");
        addDefault(l, ULTIMATE_CHAT, "&a&l✦ &6¡Lucky Boost! &a&l✦ &7¡Recibiste &c&lEXP SUPREMA &7por &e{duration}s&7!");
    }

    private static void setupPortuguese(Language l) {
        addDefault(l, XP_GAIN, "&aVocê ganhou &e{amount} Xp por {source}!");
        addDefault(l, LEVEL_UP, "&aVocê subiu de nível! Agora você é nível &e{level}&a!");
        addDefault(l, LUCKY_BOOST_EXPIRE, "&c&l✦ &6Seu Lucky Boost expirou! &c&l✦");
        addDefault(l, DOUBLE_TITLE, "&6&lVOCÊ DEU SORTE!");
        addDefault(l, DOUBLE_SUBTITLE, "&bVocê receberá &b&lEXP DUPLA &bneste jogo!");
        addDefault(l, DOUBLE_CHAT, "&a&l✦ &6Lucky Boost! &a&l✦ &7Você recebeu &b&lEXP DUPLA &7por &e{duration}s&7!");
        addDefault(l, TRIPLE_TITLE, "&d&lSUPER SORTE!");
        addDefault(l, TRIPLE_SUBTITLE, "&bVocê receberá &d&lEXP TRIPLA &bneste jogo!");
        addDefault(l, TRIPLE_CHAT, "&a&l✦ &6Lucky Boost! &a&l✦ &7Você recebeu &d&lEXP TRIPLA &7por &e{duration}s&7!");
        addDefault(l, MEGA_TITLE, "&6&lMEGA SORTE!");
        addDefault(l, MEGA_SUBTITLE, "&bVocê receberá &6&lMEGA EXP &bneste jogo!");
        addDefault(l, MEGA_CHAT, "&a&l✦ &6Lucky Boost! &a&l✦ &7Você recebeu &6&lMEGA EXP &7por &e{duration}s&7!");
        addDefault(l, ULTIMATE_TITLE, "&c&lSORTE SUPREMA!");
        addDefault(l, ULTIMATE_SUBTITLE, "&bVocê receberá &c&lEXP SUPREMA &bneste jogo!");
        addDefault(l, ULTIMATE_CHAT, "&a&l✦ &6Lucky Boost! &a&l✦ &7Você recebeu &c&lEXP SUPREMA &7por &e{duration}s&7!");
    }

    private static void setupItalian(Language l) {
        addDefault(l, XP_GAIN, "&aHai guadagnato &e{amount} Xp per {source}!");
        addDefault(l, LEVEL_UP, "&aSei salito di livello! Ora sei al livello &e{level}&a!");
        addDefault(l, LUCKY_BOOST_EXPIRE, "&c&l✦ &6Il tuo Lucky Boost è scaduto! &c&l✦");
        addDefault(l, DOUBLE_TITLE, "&6&lSEI FORTUNATO!");
        addDefault(l, DOUBLE_SUBTITLE, "&bRiceverai &b&lEXP DOPPIA &bin questa partita!");
        addDefault(l, DOUBLE_CHAT, "&a&l✦ &6Lucky Boost! &a&l✦ &7Hai ricevuto &b&lEXP DOPPIA &7per &e{duration}s&7!");
        addDefault(l, TRIPLE_TITLE, "&d&lSUPER FORTUNATO!");
        addDefault(l, TRIPLE_SUBTITLE, "&bRiceverai &d&lEXP TRIPLA &bin questa partita!");
        addDefault(l, TRIPLE_CHAT, "&a&l✦ &6Lucky Boost! &a&l✦ &7Hai ricevuto &d&lEXP TRIPLA &7per &e{duration}s&7!");
        addDefault(l, MEGA_TITLE, "&6&lMEGA FORTUNATO!");
        addDefault(l, MEGA_SUBTITLE, "&bRiceverai &6&lMEGA EXP &bin questa partita!");
        addDefault(l, MEGA_CHAT, "&a&l✦ &6Lucky Boost! &a&l✦ &7Hai ricevuto &6&lMEGA EXP &7per &e{duration}s&7!");
        addDefault(l, ULTIMATE_TITLE, "&c&lFORTUNATISSIMO!");
        addDefault(l, ULTIMATE_SUBTITLE, "&bRiceverai &c&lEXP SUPREMA &bin questa partita!");
        addDefault(l, ULTIMATE_CHAT, "&a&l✦ &6Lucky Boost! &a&l✦ &7Hai ricevuto &c&lEXP SUPREMA &7per &e{duration}s&7!");
    }

    private static void setupFrench(Language l) {
        addDefault(l, XP_GAIN, "&aVous avez gagné &e{amount} Xp pour {source}!");
        addDefault(l, LEVEL_UP, "&aVous avez monté de niveau! Vous êtes maintenant niveau &e{level}&a!");
        addDefault(l, LUCKY_BOOST_EXPIRE, "&c&l✦ &6Votre Lucky Boost a expiré! &c&l✦");
        addDefault(l, DOUBLE_TITLE, "&6&lVOUS AVEZ EU DE LA CHANCE!");
        addDefault(l, DOUBLE_SUBTITLE, "&bVous recevrez &b&lEXP DOUBLE &bdans ce jeu!");
        addDefault(l, DOUBLE_CHAT, "&a&l✦ &6Lucky Boost! &a&l✦ &7Vous avez reçu &b&lEXP DOUBLE &7pendant &e{duration}s&7!");
        addDefault(l, TRIPLE_TITLE, "&d&lSUPER CHANCEUX!");
        addDefault(l, TRIPLE_SUBTITLE, "&bVous recevrez &d&lEXP TRIPLE &bdans ce jeu!");
        addDefault(l, TRIPLE_CHAT, "&a&l✦ &6Lucky Boost! &a&l✦ &7Vous avez reçu &d&lEXP TRIPLE &7pendant &e{duration}s&7!");
        addDefault(l, MEGA_TITLE, "&6&lMEGA CHANCEUX!");
        addDefault(l, MEGA_SUBTITLE, "&bVous recevrez &6&lMEGA EXP &bdans ce jeu!");
        addDefault(l, MEGA_CHAT, "&a&l✦ &6Lucky Boost! &a&l✦ &7Vous avez reçu &6&lMEGA EXP &7pendant &e{duration}s&7!");
        addDefault(l, ULTIMATE_TITLE, "&c&lCHANCE ULTIME!");
        addDefault(l, ULTIMATE_SUBTITLE, "&bVous recevrez &c&lEXP ULTIME &bdans ce jeu!");
        addDefault(l, ULTIMATE_CHAT, "&a&l✦ &6Lucky Boost! &a&l✦ &7Vous avez reçu &c&lEXP ULTIME &7pendant &e{duration}s&7!");
    }

    private static void setupRussian(Language l) {
        addDefault(l, XP_GAIN, "&aВы получили &e{amount} опыта за {source}!");
        addDefault(l, LEVEL_UP, "&aВы повысили уровень! Теперь вы &e{level} уровня&a!");
        addDefault(l, LUCKY_BOOST_EXPIRE, "&c&l✦ &6Ваш Lucky Boost истёк! &c&l✦");
        addDefault(l, DOUBLE_TITLE, "&6&lВАМ ПОВЕЗЛО!");
        addDefault(l, DOUBLE_SUBTITLE, "&bВы получите &b&lДВОЙНОЙ ОПЫТ &bв этой игре!");
        addDefault(l, DOUBLE_CHAT, "&a&l✦ &6Lucky Boost! &a&l✦ &7Вы получили &b&lДВОЙНОЙ ОПЫТ &7на &e{duration}с&7!");
        addDefault(l, TRIPLE_TITLE, "&d&lСУПЕР ВЕЗЕНИЕ!");
        addDefault(l, TRIPLE_SUBTITLE, "&bВы получите &d&lТРОЙНОЙ ОПЫТ &bв этой игре!");
        addDefault(l, TRIPLE_CHAT, "&a&l✦ &6Lucky Boost! &a&l✦ &7Вы получили &d&lТРОЙНОЙ ОПЫТ &7на &e{duration}с&7!");
        addDefault(l, MEGA_TITLE, "&6&lМЕГА ВЕЗЕНИЕ!");
        addDefault(l, MEGA_SUBTITLE, "&bВы получите &6&lМЕГА ОПЫТ &bв этой игре!");
        addDefault(l, MEGA_CHAT, "&a&l✦ &6Lucky Boost! &a&l✦ &7Вы получили &6&lМЕГА ОПЫТ &7на &e{duration}с&7!");
        addDefault(l, ULTIMATE_TITLE, "&c&lНЕВЕРОЯТНОЕ ВЕЗЕНИЕ!");
        addDefault(l, ULTIMATE_SUBTITLE, "&bВы получите &c&lУЛЬТИМАТИВНЫЙ ОПЫТ &bв этой игре!");
        addDefault(l, ULTIMATE_CHAT, "&a&l✦ &6Lucky Boost! &a&l✦ &7Вы получили &c&lУЛЬТИМАТИВНЫЙ ОПЫТ &7на &e{duration}с&7!");
    }

    private static void setupTurkish(Language l) {
        addDefault(l, XP_GAIN, "&a{source} için &e{amount} TP kazandın!");
        addDefault(l, LEVEL_UP, "&aSeviye atladın! Artık &e{level}&a. seviyesin!");
        addDefault(l, LUCKY_BOOST_EXPIRE, "&c&l✦ &6Şanslı Takviyenin süresi doldu! &c&l✦");
        addDefault(l, DOUBLE_TITLE, "&6&lŞANSLISIN!");
        addDefault(l, DOUBLE_SUBTITLE, "&bBu oyunda &b&lİKİ KAT TP &balacaksın!");
        addDefault(l, DOUBLE_CHAT, "&a&l✦ &6Lucky Boost! &a&l✦ &e{duration}s &7boyunca &b&lİKİ KAT TP &7kazandın!");
        addDefault(l, TRIPLE_TITLE, "&d&lSÜPER ŞANSLISIN!");
        addDefault(l, TRIPLE_SUBTITLE, "&bBu oyunda &d&lÜÇ KAT TP &balacaksın!");
        addDefault(l, TRIPLE_CHAT, "&a&l✦ &6Lucky Boost! &a&l✦ &e{duration}s &7boyunca &d&lÜÇ KAT TP &7kazandın!");
        addDefault(l, MEGA_TITLE, "&6&lMEGA ŞANSLISIN!");
        addDefault(l, MEGA_SUBTITLE, "&bBu oyunda &6&lMEGA TP &balacaksın!");
        addDefault(l, MEGA_CHAT, "&a&l✦ &6Lucky Boost! &a&l✦ &e{duration}s &7boyunca &6&lMEGA TP &7kazandın!");
        addDefault(l, ULTIMATE_TITLE, "&c&lULTIMATE ŞANSLISIN!");
        addDefault(l, ULTIMATE_SUBTITLE, "&bBu oyunda &c&lULTIMATE TP &balacaksın!");
        addDefault(l, ULTIMATE_CHAT, "&a&l✦ &6Lucky Boost! &a&l✦ &e{duration}s &7boyunca &c&lULTIMATE TP &7kazandın!");
    }

    private static void setupPolish(Language l) {
        addDefault(l, XP_GAIN, "&aZyskałeś &e{amount} XP za {source}!");
        addDefault(l, LEVEL_UP, "&aAwansowałeś! Masz teraz &e{level} poziom&a!");
        addDefault(l, LUCKY_BOOST_EXPIRE, "&c&l✦ &6Twój Lucky Boost wygasł! &c&l✦");
        addDefault(l, DOUBLE_TITLE, "&6&lMASZ SZCZĘŚCIE!");
        addDefault(l, DOUBLE_SUBTITLE, "&bOtrzymasz &b&lPODWÓJNY XP &bw tej grze!");
        addDefault(l, DOUBLE_CHAT, "&a&l✦ &6Lucky Boost! &a&l✦ &7Otrzymałeś &b&lPODWÓJNY XP &7na &e{duration}s&7!");
        addDefault(l, TRIPLE_TITLE, "&d&lSUPER SZCZĘŚCIE!");
        addDefault(l, TRIPLE_SUBTITLE, "&bOtrzymasz &d&lPOTRÓJNY XP &bw tej grze!");
        addDefault(l, TRIPLE_CHAT, "&a&l✦ &6Lucky Boost! &a&l✦ &7Otrzymałeś &d&lPOTRÓJNY XP &7na &e{duration}s&7!");
        addDefault(l, MEGA_TITLE, "&6&lMEGA SZCZĘŚCIE!");
        addDefault(l, MEGA_SUBTITLE, "&bOtrzymasz &6&lMEGA XP &bw tej grze!");
        addDefault(l, MEGA_CHAT, "&a&l✦ &6Lucky Boost! &a&l✦ &7Otrzymałeś &6&lMEGA XP &7na &e{duration}s&7!");
        addDefault(l, ULTIMATE_TITLE, "&c&lULTIMATE SZCZĘŚCIE!");
        addDefault(l, ULTIMATE_SUBTITLE, "&bOtrzymasz &c&lULTIMATE XP &bw tej grze!");
        addDefault(l, ULTIMATE_CHAT, "&a&l✦ &6Lucky Boost! &a&l✦ &7Otrzymałeś &c&lULTIMATE XP &7na &e{duration}s&7!");
    }

    private static void setupChinese(Language l) {
        addDefault(l, XP_GAIN, "&a你获得了 &e{amount} 经验值 ({source})!");
        addDefault(l, LEVEL_UP, "&a你升级了！你现在的等级是 &e{level}&a!");
        addDefault(l, LUCKY_BOOST_EXPIRE, "&c&l✦ &6你的幸运加成已过期！ &c&l✦");
        addDefault(l, DOUBLE_TITLE, "&6&l你真幸运！");
        addDefault(l, DOUBLE_SUBTITLE, "&b你将在本局游戏中获得 &b&l双倍经验&b！");
        addDefault(l, DOUBLE_CHAT, "&a&l✦ &6幸运加成！ &a&l✦ &7你获得了 &b&l双倍经验&7，持续 &e{duration}秒&7！");
        addDefault(l, TRIPLE_TITLE, "&d&l超级幸运！");
        addDefault(l, TRIPLE_SUBTITLE, "&b你将在本局游戏中获得 &d&l三倍经验&b！");
        addDefault(l, TRIPLE_CHAT, "&a&l✦ &6幸运加成！ &a&l✦ &7你获得了 &d&l三倍经验&7，持续 &e{duration}秒&7！");
        addDefault(l, MEGA_TITLE, "&6&l极其幸运！");
        addDefault(l, MEGA_SUBTITLE, "&b你将在本局游戏中获得 &6&l五倍经验&b！");
        addDefault(l, MEGA_CHAT, "&a&l✦ &6幸运加成！ &a&l✦ &7你获得了 &6&l五倍经验&7，持续 &e{duration}秒&7！");
        addDefault(l, ULTIMATE_TITLE, "&c&l终极幸运！");
        addDefault(l, ULTIMATE_SUBTITLE, "&b你将在本局游戏中获得 &c&l十倍经验&b！");
        addDefault(l, ULTIMATE_CHAT, "&a&l✦ &6幸运加成！ &a&l✦ &7你获得了 &c&l十倍经验&7，持续 &e{duration}秒&7！");
    }

    private static void setupIndonesian(Language l) {
        addDefault(l, XP_GAIN, "&aAnda mendapatkan &e{amount} XP untuk {source}!");
        addDefault(l, LEVEL_UP, "&aAnda naik level! Anda sekarang level &e{level}&a!");
        addDefault(l, LUCKY_BOOST_EXPIRE, "&c&l✦ &6Lucky Boost Anda telah berakhir! &c&l✦");
        addDefault(l, DOUBLE_TITLE, "&6&lANDA BERUNTUNG!");
        addDefault(l, DOUBLE_SUBTITLE, "&bAnda akan menerima &b&lDOUBLE EXP &bdalam game ini!");
        addDefault(l, DOUBLE_CHAT, "&a&l✦ &6Lucky Boost! &a&l✦ &7Anda menerima &b&lDOUBLE EXP &7selama &e{duration}d&7!");
        addDefault(l, TRIPLE_TITLE, "&d&lSANGAT BERUNTUNG!");
        addDefault(l, TRIPLE_SUBTITLE, "&bAnda akan menerima &d&lTRIPLE EXP &bdalam game ini!");
        addDefault(l, TRIPLE_CHAT, "&a&l✦ &6Lucky Boost! &a&l✦ &7Anda menerima &d&lTRIPLE EXP &7selama &e{duration}d&7!");
        addDefault(l, MEGA_TITLE, "&6&lMEGA BERUNTUNG!");
        addDefault(l, MEGA_SUBTITLE, "&bAnda akan menerima &6&lMEGA EXP &bdalam game ini!");
        addDefault(l, MEGA_CHAT, "&a&l✦ &6Lucky Boost! &a&l✦ &7Anda menerima &6&lMEGA EXP &7selama &e{duration}d&7!");
        addDefault(l, ULTIMATE_TITLE, "&c&lULTIMATE BERUNTUNG!");
        addDefault(l, ULTIMATE_SUBTITLE, "&bAnda akan menerima &c&lULTIMATE EXP &bdalam game ini!");
        addDefault(l, ULTIMATE_CHAT, "&a&l✦ &6Lucky Boost! &a&l✦ &7Anda menerima &c&lULTIMATE EXP &7selama &e{duration}d&7!");
    }

    private static void setupHindi(Language l) {
        addDefault(l, XP_GAIN, "&aआपको {source} के लिए &e{amount} XP मिला!");
        addDefault(l, LEVEL_UP, "&aआपका लेवल बढ़ गया! अब आप लेवल &e{level} पर हैं&a!");
        addDefault(l, LUCKY_BOOST_EXPIRE, "&c&l✦ &6आपका लकी बूस्ट समाप्त हो गया है! &c&l✦");
        addDefault(l, DOUBLE_TITLE, "&6&lआप भाग्यशाली हैं!");
        addDefault(l, DOUBLE_SUBTITLE, "&bआपको इस गेम में &b&lDOUBLE EXP &bमिलेगा!");
        addDefault(l, DOUBLE_CHAT, "&a&l✦ &6लकी बूस्ट! &a&l✦ &7आपको &e{duration}s &7के लिए &b&lDOUBLE EXP &7मिला!");
        addDefault(l, TRIPLE_TITLE, "&d&lसुपर भाग्यशाली!");
        addDefault(l, TRIPLE_SUBTITLE, "&bआपको इस गेम में &d&lTRIPLE EXP &bमिलेगा!");
        addDefault(l, TRIPLE_CHAT, "&a&l✦ &6लकी बूस्ट! &a&l✦ &7आपको &e{duration}s &7के लिए &d&lTRIPLE EXP &7मिला!");
        addDefault(l, MEGA_TITLE, "&6&lमेगा भाग्यशाली!");
        addDefault(l, MEGA_SUBTITLE, "&bआपको इस गेम में &6&lMEGA EXP &bमिलेगा!");
        addDefault(l, MEGA_CHAT, "&a&l✦ &6लकी बूस्ट! &a&l✦ &7आपको &e{duration}s &7के लिए &6&lMEGA EXP &7मिला!");
        addDefault(l, ULTIMATE_TITLE, "&c&lअल्टीमेट भाग्यशाली!");
        addDefault(l, ULTIMATE_SUBTITLE, "&bआपको इस गेम में &c&lULTIMATE EXP &bमिलेगा!");
        addDefault(l, ULTIMATE_CHAT, "&a&l✦ &6लकी बूस्ट! &a&l✦ &7आपको &e{duration}s &7के लिए &c&lULTIMATE EXP &7मिला!");
    }

    private static void setupPersian(Language l) {
        addDefault(l, XP_GAIN, "&aشما &e{amount} XP برای {source} دریافت کردید!");
        addDefault(l, LEVEL_UP, "&aسطح شما بالا رفت! شما اکنون در سطح &e{level} هستید&a!");
        addDefault(l, LUCKY_BOOST_EXPIRE, "&c&l✦ &6بوست خوش‌شانسی شما تمام شد! &c&l✦");
        addDefault(l, DOUBLE_TITLE, "&6&lشما خوش‌شانس بودید!");
        addDefault(l, DOUBLE_SUBTITLE, "&bشما در این بازی &b&lتجربه دوبرابر &bدریافت خواهید کرد!");
        addDefault(l, DOUBLE_CHAT, "&a&l✦ &6بوست خوش‌شانسی! &a&l✦ &7شما &b&lتجربه دوبرابر &7برای &e{duration} ثانیه &7دریافت کردید!");
        addDefault(l, TRIPLE_TITLE, "&d&lبسیار خوش‌شانس!");
        addDefault(l, TRIPLE_SUBTITLE, "&bشما در این بازی &d&lتجربه سه‌برابر &bدریافت خواهید کرد!");
        addDefault(l, TRIPLE_CHAT, "&a&l✦ &6بوست خوش‌شانسی! &a&l✦ &7شما &d&lتجربه سه‌برابر &7برای &e{duration} ثانیه &7دریافت کردید!");
        addDefault(l, MEGA_TITLE, "&6&lمگا خوش‌شانس!");
        addDefault(l, MEGA_SUBTITLE, "&bشما در این بازی &6&lتجربه مگا &bدریافت خواهید کرد!");
        addDefault(l, MEGA_CHAT, "&a&l✦ &6بوست خوش‌شانسی! &a&l✦ &7شما &6&lتجربه مگا &7برای &e{duration} ثانیه &7دریافت کردید!");
        addDefault(l, ULTIMATE_TITLE, "&c&lنهایت خوش‌شانسی!");
        addDefault(l, ULTIMATE_SUBTITLE, "&bشما در این بازی &c&lتجربه نهایی &bدریافت خواهید کرد!");
        addDefault(l, ULTIMATE_CHAT, "&a&l✦ &6بوست خوش‌شانسی! &a&l✦ &7شما &c&lتجربه نهایی &7برای &e{duration} ثانیه &7دریافت کردید!");
    }

    private static void setupBengali(Language l) {
        addDefault(l, XP_GAIN, "&aআপনি {source} এর জন্য &e{amount} XP পেয়েছেন!");
        addDefault(l, LEVEL_UP, "&aআপনার লেভেল বেড়েছে! আপনি এখন লেভেল &e{level}&a!");
        addDefault(l, LUCKY_BOOST_EXPIRE, "&c&l✦ &6আপনার লাকি বুস্ট শেষ হয়ে গেছে! &c&l✦");
        addDefault(l, DOUBLE_TITLE, "&6&lআপনি ভাগ্যবান!");
        addDefault(l, DOUBLE_SUBTITLE, "&bআপনি এই গেমে &b&lDOUBLE EXP &bপাবেন!");
        addDefault(l, DOUBLE_CHAT, "&a&l✦ &6লাকি বুস্ট! &a&l✦ &7আপনি &e{duration}s &7এর জন্য &b&lDOUBLE EXP &7পেয়েছেন!");
        addDefault(l, TRIPLE_TITLE, "&d&lসুপার ভাগ্যবান!");
        addDefault(l, TRIPLE_SUBTITLE, "&bআপনি এই গেমে &d&lTRIPLE EXP &bপাবেন!");
        addDefault(l, TRIPLE_CHAT, "&a&l✦ &6লাকি বুস্ট! &a&l✦ &7আপনি &e{duration}s &7এর জন্য &d&lTRIPLE EXP &7পেয়েছেন!");
        addDefault(l, MEGA_TITLE, "&6&lমেগা ভাগ্যবান!");
        addDefault(l, MEGA_SUBTITLE, "&bআপনি এই গেমে &6&lMEGA EXP &bপাবেন!");
        addDefault(l, MEGA_CHAT, "&a&l✦ &6লাকি বুস্ট! &a&l✦ &7আপনি &e{duration}s &7এর জন্য &6&lMEGA EXP &7পেয়েছেন!");
        addDefault(l, ULTIMATE_TITLE, "&c&lচরম ভাগ্যবান!");
        addDefault(l, ULTIMATE_SUBTITLE, "&bআপনি এই গেমে &c&lULTIMATE EXP &bপাবেন!");
        addDefault(l, ULTIMATE_CHAT, "&a&l✦ &6লাকি বুস্ট! &a&l✦ &7আপনি &e{duration}s &7এর জন্য &c&lULTIMATE EXP &7পেয়েছেন!");
    }

    private static void addDefault(@NotNull Language l, String path, Object value) {
        if (!l.exists(path)) {
            l.set(path, value);
        }
    }
}