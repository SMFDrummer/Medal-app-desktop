package io.github.smfdrummer.medal_app_desktop.ui.utils.strategy

import io.github.smfdrummer.utils.strategy.buildStrategy

fun 账号激活() = buildStrategy {
    version = 2
    description = "账号初始化激活"

    packet {
        i = "V206"

        parse("""
            {
              "m": "13edcdb93b85e02e758b8831e3e8696e",
              "pi": "{{pi}}",
              "pr": "H4sIAAAAAAAA_61cSXfiurb-L3f8Bm6g7q1hCGDMK4uHAxhpFuycGHdhXZK4-fXv23IfTCjqnAErbiRt7b6T86-XfFmIR_MH_uZubv6wNm5qFWax2swydkyPh6P5w4wydTWdR3yXqcwQkUV_Cz5ic_zdhDlT6S8v6H41XR6tJ_NsxtZxdVQyqwhHLAjz1cZSfz0u9Wcdz4NZyoL1mNYXsQjM4O1o5SPlV7A-YszZc-Yp7ck80jo2vc9Znh69_fLDDE4H65hqv6azdP-E_cU73Y0ZjcmsKVdWG445Y8VNlgWNpTHews65xO1BY9NQAwzFjfv78OJyjKMqZzMRodBozdMBdAmxfijXD9YY_5BjPj070zPQYcymbv7XjuZhDf3huIrePYx59xbL_r4S--s671_3cXDWdJ-z6QPt88j35nEVC_-wYBHu8ZfePwBHP5Ww2ncJ7ZODXyKen11tizFezjX7XNPhebFM3e77hJ0l_HaN0NUnZx6vCYd_m_FSPcT20TNCwNwq1nQm9-Q54_CgyWc5cNPrZzyRez9X8yLvUcpTttq8qlawTa29cgY9q7H2CbRQgWdqYW882X0I46d8ZgXdZ0uFaFbDEHsJQ7c2kKuNqVrT-c9fi3Mz9vnJ_ME269SamvU-PstnJp69nhucaG95DWOnlLycjVfTbc42D9lfT9kA_g_FJf7b_O_izx4v8M8h4138i-_wJ966-13EiZcl30LXYecD4Dv5MhJ6lHpT90jjeGxnXCPdXH4eQnE6xFHx_ChpFXpGFDZ70lWS4cxzdkexsGqZTvBeY5ttIXVXc4kGkKMTdO0txXvIm9uVp5gnkFHoheeQPIqja5id-7Hukjx35FsYF89o7Ww1BU2CNWBNFPeLDSE88fzkxnNFbGapSfqsKfWe9YOzCytd1itZiqp7ojfJ9Bvtv-R5WPHwFX8nUrZIfogfz8TPheThqeLJl3nrZp7kSaNrO7mOl5frCKzn6NW6TxfrZgOw8gFYA_u2Lve92V48A38vn-UXMAfWv1zLKi6frR4vcWKXz8YDdE-_o2kDM7jcvxWYl_uY1nPn1_mR9_kxRJtBHl0-G-ARV36HRwO0UQfWGuDtwFqXfBxdrhUOrDW0_sNVeZb3ZF-K2RnyWvya8rO1wXXgnmke7NOZ1rSCCemgRvQhmrlaX3ddsg9dXTZ-Fh5sEvQz5k5WCDlnrHqOtE1H2PUfsMNffxQ3JOJI-3mg_ei0H3jws1Wsz0QnwKZ4RvkFHwCeK9ZjSjKpsae0sKTPt9PKhkXeYuK3scPko3lujFP-JHEe_5qaKX4Zfjl-wN_E2qaKH2ITE_DNEX4YZ2HcFvcWxloYa2GshbGIh6YWxlr0DmO3GGdhPMf7Na7XGL_GeJfu6Yc5a8yB3k_XmLPGnDXGbzEOPmi6xZgtxmwxZosxW1qT1qPfqIyVtrCJ50zGAvqbvMYeJT0w9gz6YI7EDc_d8wo0IzrhfcZySa8M9Mqg3zp-Od7lpGNW8Eq4jjAux_N30DrHWgWNoTVWkAUmxz2kJS5EkxlghEWHfmpJH6LjK42Dv5ZxgCL_0j4DT9pzVzvXtl3GM6S7Mk5NJp-etsu9_UThDlOeHeYfDKY-a1uSj1OrAyPyf6prID5y7GhfiL9cLfv04OeacWU8NyY9IL9xcFKa83HQxhH5F_gm8suE6_GQVNfw74hXc5oj48h4nlfzfSnn5Z5xPUlr-eJ74LAo3_1F8uYoHf-Yvb8cyf6MSlpI2xSW_h-0QmydSb1NJhIO-IE4dlTGh8kkkPHHI8WUIuzqE_YeVDJ99hDPy3HBTGHFemQVXJE2ZGFHRFursGitks4lToXUe6kzy8DV7aDGBfFn4Rlpyxst82s_y_XJqfK9J66l0iZLO-OwtPGvThlTlzyKAteo7WUUkI-v4qdmLbLdw3P49TlP1-aY-tW9bR5G7bvl13dXYa2uwpJx9DCsoLuejMECvrdjmb9QnEjXFV2lTu8nyaHmR6ymQts18f4L4nte-7ZqfVbKUYcPrLTlxlwXLY8RF4pPrPNv8HhSxrEm9JCnxKeXPeUUbRzoGWx8MMiuh5CfB_J5CvKnM-3516ay7ZS_Gfbpy7ix2NuntfZTPST2WuyXM76PHPwtnp2fH_3cKYL9jaYHfffhzSeRG9r-C-ETbFXILO25pNnGhE16JdlWXpydzAc6-U7qGVmZkwXmaDWFr4MOuItJ4JZ7xTrvP8iWYB1tJWXa0-rY1tPe6-s3T_sP2c-caCeMTKn16WDU-Vl2fql0EvYhp3wW-aAqSnsBu7Pu6Pn8DD8TVmso7Rp-IhbdeDk7k42q12_0e7Es2ud-SLi0OaBduEkTv78JfSnlA_NgY-Erv_6dE_-z1I3VkD3VOTgDPzzix5EHpiamD8h5t2OmiYBry1BMd0fkOCMez1IRzMZkq5ghbSny_XYufLG2mgrfcnaRNQ1zgeuVwbOVsQvkeputyjUztzayDpAQHmawLpDDBjzwIh48FHyz9JmD_Die6ZirsiDUV9PZCPcZ2WVXZyehkX03FVi5jGnsiBysWE1d7M0crxwL-dUrZCRMgQdkeqszyn8SsldRIv1IMDmKqZux4FXjG9hF5CIrZ5axze7IYkuVcu9sNcrRK1_QwBSQdeRh-cphyLUsxYrXWIPnq42r8sAPefCK98KHbpCsUW5zZMW2gJ1VYUvGVA-BTKcinhXIV7HPecgkXTlwlHRJJT-PZso2XiCCbcZpXxsWIhf0V5u1CtqDLmYhpnPQ2VWFxG9SzxszZxnjBzgsZIGFPBK-xOHg284XGxEhB6a1I3GU84rDQs5TRRAFvPAjTnmnBv_sMKxjR6BVATzHK2OdQqf8FdV_kl0q-ReTfEzwi3zLsEaWxuIV4iPhrAsWzMFXF3xfA4flUeZtjheX81iwMraZMMDbwPOtWITgYcGmk4hyaraZHwX2jdw9LPO9naSlMOyQTUMVfPeJ7gyazRyuEX3EJtSsGM-LmcKLkGip8r3MhWOyIwL2jU3nsdjMQQc7tqagqcNhX0KdF6-gz1qXNInnFW6QAWcJuaTagBcCjio2kKnAjwRgrjbzAPmXzpGr0R75nlXz7JB8ihXYAeEEGVSYZinCEfEK8r3aRLDxkNtiq8t5TlSU-rOLsW4OekGPXOwLMZdjB1RLIvmHXqTgH-TAlXLC956cR2sI2LjGRmn2qamvJTvcv5dxrVrqPt-T_5J-W167ZV0gk_WTRPgu2fDonO5L_3QWFH84YVV78N4JZmV7qQZFvuK4iX_mB2d-lvpSxyvFOmUFhy3m5I9idyFjL6ozQf5CnU2tUi_L_EcF_cfkD1gi_WxyIPsk_YLkpfSDZuj7bqknH9V6iD8h8-AfK2uQNRwdsRN4NEst5DAtHFNDDoOfWfnsK_gYu0Q447CLD5u6I9A-Z5QbtHAKaWMKPN-YHThr6Pssg96nVa5W4hN72L_nwx-qVPtr8Rp_wib7B1mrGVdrzFLgpqyQQ0AmqL5SP4fcPxRWsAZttx2Y7hhjC1aEGtv_DswLWo6AG3DhZD878Dj2sQa8WZnj1fwNHnTYWvDTLH4PxyxC_PIh-jjC3s5gv038th2YMvaFnXOVHszNmuJY2BaedvkHO6E9I4b_np5mSnwHTxTKyUWz5gPVeUdSLnuwLPiIB7Lbak8mqfYXR1LWhSbzBhNxfk9WqMYH3mVEoz6s1ww5lb7abDWKb1tYLtXYEfPPVEv_FpYlHC86fNEB7BOwXPjEbVfXxiSXK_ixMsZqdAB6CT8yfYWOeDI-FVRPOy5htxGrGK-35HLEEN-xzSvstdnhGeFlQjZnWZeOdE-0hQz1dA423T8s7Ldv6SjjUvCL8ugWL8gG6fxMI_tCsVvzfPoAf7emmCG9AWuIjpB_kmnY6-C1q2_wR9sx2ZnVtKvjM-AfQv9D0HPe0lHWtL6xixRzUL0YvsvqyQb8U0HxNOz-sSsbiBU2PCO_t-romZSrq_K3HcP-FlRH7tANNgr-GDJNOXsHDw04aNL_Fv4wHgP2cDVFzFKQn5qNu7whGyTr4j1abSFvNH49YovSr7jG2G9y6WTyUfslmfcsJr6ofU4yieidkPE_yZJJ8FLIqv9c9lMymRdU_gr5U7MOaHiq8pwyNztKe1T2ccrrVLTPP5_lHqiOIOdWfnkScthEjvgC8Z8Ov41Y4jW1YvvIHMQMMaKegvRa6k2byznZf8WXfk2bZ3sfsi7QqYOLhderiwkjKoZq3zxZjvo5wDws-0wPss7vGqzF30BO1OTzk8Br-1cBd3YJxilusotkrWDhfXBHjQhnxAQ5clFZC3G18WevvuBkkQuMG145WfLsyLqfrJH2e1C79LnszZU9H01oVZ3g_WB4yKWZImsuZeySPuvbLk3OIvbq3CsWhuf39mGIxDOoHkI5gokfYtbH6jnlVUPPjdnFcxHbCXdGvb4GwerhEVPPcN6Dz_eI2ntj1B9e3uTqMueUPSgtS9re4wT5mtn0WygO7PCA-o7d-9NBox5jc68JQ6RNHp8IBXpSeMbPD_E08MxZqi9Vb0TmsZr6TrWdljfzt8Oi138MBNV3pBzZedU3DtwqD3UXrM5hoXt1Dhv1e46xL-d17_swJ29uXue1ra6BRpHU05gposqreWJH9fuDkeVNPozreg039ptaiStlpb6ef9TjQdO6xlvm5bJ-tmzqZ9DzpK6vIddW6rUpl250B7or4Bd7_dhk0qu7ucnys14TOXEidKuGBRtoJz3ZQS5a9bmqXh_8znRWyk1S0xbX-rKW_3euqWr7LipczZM5NOmVQA7fg3escDPmRcfGxs9Vvcg1RNmLpBxZ2glx9KRcqdL-994_fvu-WH0_n-rE376n2tG374833t-Az27At27At27Av0G_4gb98hv0y2_QL79Bv_wG_fIb9Mtv0C-_Qb_8Bv3yG_TLb9Avu0G_7Ab9shv0y27QL721_g380xv0zW68T2_sL7tBv_TG_rIb8pHeot8N_qY35Ce78T69tb8b8pF-3V8Zf-5y71jb8rHajzs8te4NeZra9Hq8ZKJwbV37ds1d2LkbN_c58o-Pul5D_atDGYPmdO5mq1ONZZzY8Q6-O9J4mctqPJ5XeQ1yNZqn27qsYcrajn3qri90uzmnIWTfRl7DN2TyDEVZ_0Z8GgsZ0__vfJIjvvA58svnvT3eajvl_4zow5uOTnKPsUBu0jurQbnKWfRiT8SXHbyoD1Gdi0plD0KfKI1fiscBaFL5PbpnfvuO-mbN-Rafa43fI1rJ_TsF9WRnWtnDnJHe5fhbVHWqEDlQE5MKY97GC7GtNHF3wnx-bM6e5R542vaCI8QKvfMtH67OujHym7uYfH6Jb9Iv51mUl70X9OPz5Q834U2N_tn4qbpNPLg7uwuR9uII3Df1_sQODvumthcg1m76aYgHznXcAj8v-wLk24UxrnIlqn8Tz23gUZ9dsj88Q5Fn98r82P6ocqiUaj9Ex6rfmZXXNuXP6sDY0R1jx78_1ko7Y9XO2GJgbHZl7NC6-dWxshc8QS7Z8Ch9duZhm0PNP9ueLyvHJWfQf37q9Z9InuNWtuU5rFrOO9cy5q6fx2EnH2vzNqydlDDVnzIu16K4io8TmRd3Y3Zt91nLAde86PmxvZb1gRIW5tl5LUeY88Y7cw6dOVyjHOnho75vZFWu8bO0d7IX7su6cqf_FslcumMvnhfLvLGVMfRH5j7nTi8qyut82UuWRaunk6KmM_KOUxP3yucZ8rjO_Z59NrzCWB7_p-m3c-oXHas8Ros-eJ9f1Ifs6R6Nl2f_oB9EJ8xp7Ai942Uf6Fi9S8vebMWjpkfHQh53rxsehF5ju72gI29vWKvOrdRDnH1y7V3mSU4hz9fUz5C_tPKCHEer43zk7mfx2F639LJ1sWBabZ8PezuXe5Y9YxpjFtW5x0D2Xh_bc7RVT5v2lVtFnb9TL3fdnukp66KlvQms6lwm-_ScsdL2d0XVA6_hWdpvwJN5wS14q43bg8cCa8yGYGL_XZirzSv2YXfGWNX50c5aG-oB-v0x8jzADfw26x4stqG-lv11TPYbNBhbfRpoVvGq1Ge_Khoo5bmR7_fEyjMBl_B2Mrc9H0hvE6r7ZcqXs9TJ4es5Z4yv9aDMzce-1MdSLs90z49Nrv7R6Gqsfh4ScXLLszWyVyV0AZui9u4PWha2Orf0axnnml3U14evPjaZHHlrIwC3srEJO3nt-VbQg-Kp7lmZU7OmoLFNvo-8ed_g9OEazZ4_RbxsfMNBs2U8JPeklTWTsvYnz4XUsd6ZamBlzDEPJK0lPbxjWw_0U0_rndV-53qnDhCLtg6gL4uGPvumnpd6ToPn-yGZJHU8UPm58vzzxtIo5qU45Xn_SrXj3SFmb4idFKrplPI3Vg5P9dnmsGB0XpvsihGNCKfdbre0Z8sT4qrtM-D_zpxNvNPFfrl2Ed8KktNSj8tYpKyBjK2Cl2egENe-UN0zWsIXUY_Ki9byrHQW_da8kH268ViF3sCPz35vf85PrOmfX7ZM9sRKvVDTjn9-J9_Snsuxz_LcuIxXpb7CnpiZ7N-T_5GxllfqqDy3YPtmeYYUvNzWZ7wUOifDimWZczi20sYZalqtL2E_L8TxC7ysOudzCe_pG3iBO7YC1rENs9JmJ8gF6lpS6Yto7aKsl0qbnoun-vuRdc6KMK_6VHWsWeZxi-VnNTep1icdVHgds-p0Nptk8YF6R9QvKjo2Dzht_wGc6FuA2RBO-V04BVVufIGTeQ9OeeVf75OLQZzWfx-nyo9f4mTdgxPFDPkgTvk9OJHPtYZwKu7FSdZI_j5O6j-DE_9ncBrUp7txGv0pTqVNGhdiYdfnRVpdH5Sh67qOOAV2-jXt6_oVGbqu63QmtpDn-np64d6pF_T9mzXqxVF_QO8BnGS9fAgndhdO7jUZ0u_C6Q9sMn27hv3kX_g0iNN3fGJU998sv_iZ7Z1-Rvb-x1bSx4kN2uS7cdL_Pk5_4mcGcPoDPzOA05_4mSs43etnhnFigzbibpy0v4-TdY1P39jkAZyKa3z6zkZc4FRc5dN1m3wFp0E-3Y3TMJ_uxmmYT3fhtL7GJ-UenNj0Gp_W9-CkXOOTjDXuwmmQT3fjNMynu3Ea5NP9OA363PtwuhpHbO_BSb0WR3zncy9x2l6LRdV7cRrm0904DfPpbpwG-XQfTsE1PvF7cNKu8ukuW86v8Um7F6fhnOFunAZzhvtxGoz37sdpMN67Cyf9Wl2B3WXL3Wt1he9i2EucimsxrHsvTsNx-d04Dcawd-M0zKffwKmsSwUH3evXipKT371_NrKge3-Ie_cJfZfVnt-bh91aE49_dtf-cBftWM9ZKq7WjFW5Lt_9W9aBHZZTv7cLt3pfwfFiN-ne2__twhV7szrvaXf_X0PKApf0oO5dFbL_HYuTC1q58S54Nua50LZNzY7Hux8tDBZ6C97WUQ35vZeyKt-9ebH8dnlUfkfJfNmnrcaCDvX3YO9cG3_y8ltm6mUGoIPWnFPTdwr1dV50Cf8sNFs51DVhJ0uQP9e1W9-r6IX9B4e67gx5qd6n8nuVql_XORcZCOr71-dKNfB-sYxq-GLRzG_6eGKxbOvaC8h13SfSVL884ye_jfM9jVX72cnv-Sh_NBPvXNbo5fPkZT9RrMeuPLvl_4dwvLG7WGed-sPIou-fqX-6x9rGNm3fIV4N1or8BlbLUtH9vxHJzv9yZrTlQ7LTubNsZMyNgXd5LvGjd64wpv_90Onjxbv2-12j6dk139E252WT5an5vjJZBu11eZaiPAdM5x7pDLFX0lnG3mX9uOrlfsjcoKyrHxs6xnbuaj_r_7_z1j8rulMantd8zTv_v4B4k3S-DUyqeF2eV2Sd65NerZO_xLMapx_1mnjf9rGTSWi1Zx5D66lzfWzGt-cmk1PcrsNi0ev12rE8A9o9K7Fgsjdc1YfonDadz0jL_xUg7Xgm_SU9C3zZszjs7bjLN88Q8oxi_S2lu-jsedHu2dvTN3wPtYxA56UcVr3tZdq8gx7xLq6GPGtRn-cIenIY-wnX6FvPzlkMgynNt9eOqje80rLkYLRnlf_aK__zr_8Hh_B88m5JAAA,",
              "s": "eyJzZHMiOnsiZCI6MjcsImciOjUsImMiOjUwMDAwMDAwMCwiZXMiOjE3LCJjcyI6MCwicHMiOjEwLCJrcyI6MCwiZnMiOjAsImRzIjowLCJsIjoicGlyYXRlMTAiLCJ1cHMiOjEwLCJ1YXMiOjAsInBsIjoxMH19",
              "sk": "{{sk}}",
              "ui": "{{ui}}"
            }
        """.trimIndent())
    }

    packet {
        i = "V316"

        parse("""
            {
              "b": "0",
              "n": "",
              "pi": "{{pi}}",
              "sk": "{{sk}}",
              "ui": "{{ui}}"
            }

        """.trimIndent())
    }

    packet {
        i = "V900"

        parse("""
            {
              "pi": "{{pi}}",
              "pl": [
                {
                  "i": 1002,
                  "q": 1
                },
                {
                  "i": 1001,
                  "q": 1
                },
                {
                  "i": 1003,
                  "q": 1
                }
              ],
              "sk": "{{sk}}",
              "ui": "{{ui}}"
            }
        """.trimIndent())

        onSuccess { true }
    }
}