package io.github.smfdrummer.medal_app_desktop.ui.utils.strategy

import io.github.smfdrummer.utils.strategy.buildStrategy
import io.github.smfdrummer.utils.strategy.extensions.V303

fun 获取邀请码() = buildStrategy {
    version = 1
    description = "获取邀请码"

    V303(10868)
}

fun 刷邀请码_安卓(code: String) = buildStrategy {
    version = 1
    description = "安卓 - 刷邀请码"

    packet {
        i = "V316"

        parse(
            """
            {
              "b": "0",
              "n": "",
              "pi": "{{pi}}",
              "sk": "{{sk}}",
              "ui": "{{ui}}"
            }

        """.trimIndent()
        )
    }

    packet {
        i = "V206"

        repeat = 2
        parse(
            """
            {
              "m": "13edcdb93b85e02e758b8831e3e8696e",
              "pi": "{{pi}}",
              "pr": "H4sIAAAAAAAA_61cSXfiurb-L3f8Bm6g7q1hCGDMK4uHAxhpFuycGHdhXZK4-fXv23IfTCjqnAErbiRt7b6T86-XfFmIR_MH_uZubv6wNm5qFWax2swydkyPh6P5w4wydTWdR3yXqcwQkUV_Cz5ic_zdhDlT6S8v6H41XR6tJ_NsxtZxdVQyqwhHLAjz1cZSfz0u9Wcdz4NZyoL1mNYXsQjM4O1o5SPlV7A-YszZc-Yp7ck80jo2vc9Znh69_fLDDE4H65hqv6azdP-E_cU73Y0ZjcmsKVdWG445Y8VNlgWNpTHews65xO1BY9NQAwzFjfv78OJyjKMqZzMRodBozdMBdAmxfijXD9YY_5BjPj070zPQYcymbv7XjuZhDf3huIrePYx59xbL_r4S--s671_3cXDWdJ-z6QPt88j35nEVC_-wYBHu8ZfePwBHP5Ww2ncJ7ZODXyKen11tizFezjX7XNPhebFM3e77hJ0l_HaN0NUnZx6vCYd_m_FSPcT20TNCwNwq1nQm9-Q54_CgyWc5cNPrZzyRez9X8yLvUcpTttq8qlawTa29cgY9q7H2CbRQgWdqYW882X0I46d8ZgXdZ0uFaFbDEHsJQ7c2kKuNqVrT-c9fi3Mz9vnJ_ME269SamvU-PstnJp69nhucaG95DWOnlLycjVfTbc42D9lfT9kA_g_FJf7b_O_izx4v8M8h4138i-_wJ966-13EiZcl30LXYecD4Dv5MhJ6lHpT90jjeGxnXCPdXH4eQnE6xFHx_ChpFXpGFDZ70lWS4cxzdkexsGqZTvBeY5ttIXVXc4kGkKMTdO0txXvIm9uVp5gnkFHoheeQPIqja5id-7Hukjx35FsYF89o7Ww1BU2CNWBNFPeLDSE88fzkxnNFbGapSfqsKfWe9YOzCytd1itZiqp7ojfJ9Bvtv-R5WPHwFX8nUrZIfogfz8TPheThqeLJl3nrZp7kSaNrO7mOl5frCKzn6NW6TxfrZgOw8gFYA_u2Lve92V48A38vn-UXMAfWv1zLKi6frR4vcWKXz8YDdE-_o2kDM7jcvxWYl_uY1nPn1_mR9_kxRJtBHl0-G-ARV36HRwO0UQfWGuDtwFqXfBxdrhUOrDW0_sNVeZb3ZF-K2RnyWvya8rO1wXXgnmke7NOZ1rSCCemgRvQhmrlaX3ddsg9dXTZ-Fh5sEvQz5k5WCDlnrHqOtE1H2PUfsMNffxQ3JOJI-3mg_ei0H3jws1Wsz0QnwKZ4RvkFHwCeK9ZjSjKpsae0sKTPt9PKhkXeYuK3scPko3lujFP-JHEe_5qaKX4Zfjl-wN_E2qaKH2ITE_DNEX4YZ2HcFvcWxloYa2GshbGIh6YWxlr0DmO3GGdhPMf7Na7XGL_GeJfu6Yc5a8yB3k_XmLPGnDXGbzEOPmi6xZgtxmwxZosxW1qT1qPfqIyVtrCJ50zGAvqbvMYeJT0w9gz6YI7EDc_d8wo0IzrhfcZySa8M9Mqg3zp-Od7lpGNW8Eq4jjAux_N30DrHWgWNoTVWkAUmxz2kJS5EkxlghEWHfmpJH6LjK42Dv5ZxgCL_0j4DT9pzVzvXtl3GM6S7Mk5NJp-etsu9_UThDlOeHeYfDKY-a1uSj1OrAyPyf6prID5y7GhfiL9cLfv04OeacWU8NyY9IL9xcFKa83HQxhH5F_gm8suE6_GQVNfw74hXc5oj48h4nlfzfSnn5Z5xPUlr-eJ74LAo3_1F8uYoHf-Yvb8cyf6MSlpI2xSW_h-0QmydSb1NJhIO-IE4dlTGh8kkkPHHI8WUIuzqE_YeVDJ99hDPy3HBTGHFemQVXJE2ZGFHRFursGitks4lToXUe6kzy8DV7aDGBfFn4Rlpyxst82s_y_XJqfK9J66l0iZLO-OwtPGvThlTlzyKAteo7WUUkI-v4qdmLbLdw3P49TlP1-aY-tW9bR5G7bvl13dXYa2uwpJx9DCsoLuejMECvrdjmb9QnEjXFV2lTu8nyaHmR6ymQts18f4L4nte-7ZqfVbKUYcPrLTlxlwXLY8RF4pPrPNv8HhSxrEm9JCnxKeXPeUUbRzoGWx8MMiuh5CfB_J5CvKnM-3516ay7ZS_Gfbpy7ix2NuntfZTPST2WuyXM76PHPwtnp2fH_3cKYL9jaYHfffhzSeRG9r-C-ETbFXILO25pNnGhE16JdlWXpydzAc6-U7qGVmZkwXmaDWFr4MOuItJ4JZ7xTrvP8iWYB1tJWXa0-rY1tPe6-s3T_sP2c-caCeMTKn16WDU-Vl2fql0EvYhp3wW-aAqSnsBu7Pu6Pn8DD8TVmso7Rp-IhbdeDk7k42q12_0e7Es2ud-SLi0OaBduEkTv78JfSnlA_NgY-Erv_6dE_-z1I3VkD3VOTgDPzzix5EHpiamD8h5t2OmiYBry1BMd0fkOCMez1IRzMZkq5ghbSny_XYufLG2mgrfcnaRNQ1zgeuVwbOVsQvkeputyjUztzayDpAQHmawLpDDBjzwIh48FHyz9JmD_Die6ZirsiDUV9PZCPcZ2WVXZyehkX03FVi5jGnsiBysWE1d7M0crxwL-dUrZCRMgQdkeqszyn8SsldRIv1IMDmKqZux4FXjG9hF5CIrZ5axze7IYkuVcu9sNcrRK1_QwBSQdeRh-cphyLUsxYrXWIPnq42r8sAPefCK98KHbpCsUW5zZMW2gJ1VYUvGVA-BTKcinhXIV7HPecgkXTlwlHRJJT-PZso2XiCCbcZpXxsWIhf0V5u1CtqDLmYhpnPQ2VWFxG9SzxszZxnjBzgsZIGFPBK-xOHg284XGxEhB6a1I3GU84rDQs5TRRAFvPAjTnmnBv_sMKxjR6BVATzHK2OdQqf8FdV_kl0q-ReTfEzwi3zLsEaWxuIV4iPhrAsWzMFXF3xfA4flUeZtjheX81iwMraZMMDbwPOtWITgYcGmk4hyaraZHwX2jdw9LPO9naSlMOyQTUMVfPeJ7gyazRyuEX3EJtSsGM-LmcKLkGip8r3MhWOyIwL2jU3nsdjMQQc7tqagqcNhX0KdF6-gz1qXNInnFW6QAWcJuaTagBcCjio2kKnAjwRgrjbzAPmXzpGr0R75nlXz7JB8ihXYAeEEGVSYZinCEfEK8r3aRLDxkNtiq8t5TlSU-rOLsW4OekGPXOwLMZdjB1RLIvmHXqTgH-TAlXLC956cR2sI2LjGRmn2qamvJTvcv5dxrVrqPt-T_5J-W167ZV0gk_WTRPgu2fDonO5L_3QWFH84YVV78N4JZmV7qQZFvuK4iX_mB2d-lvpSxyvFOmUFhy3m5I9idyFjL6ozQf5CnU2tUi_L_EcF_cfkD1gi_WxyIPsk_YLkpfSDZuj7bqknH9V6iD8h8-AfK2uQNRwdsRN4NEst5DAtHFNDDoOfWfnsK_gYu0Q447CLD5u6I9A-Z5QbtHAKaWMKPN-YHThr6Pssg96nVa5W4hN72L_nwx-qVPtr8Rp_wib7B1mrGVdrzFLgpqyQQ0AmqL5SP4fcPxRWsAZttx2Y7hhjC1aEGtv_DswLWo6AG3DhZD878Dj2sQa8WZnj1fwNHnTYWvDTLH4PxyxC_PIh-jjC3s5gv038th2YMvaFnXOVHszNmuJY2BaedvkHO6E9I4b_np5mSnwHTxTKyUWz5gPVeUdSLnuwLPiIB7Lbak8mqfYXR1LWhSbzBhNxfk9WqMYH3mVEoz6s1ww5lb7abDWKb1tYLtXYEfPPVEv_FpYlHC86fNEB7BOwXPjEbVfXxiSXK_ixMsZqdAB6CT8yfYWOeDI-FVRPOy5htxGrGK-35HLEEN-xzSvstdnhGeFlQjZnWZeOdE-0hQz1dA423T8s7Ldv6SjjUvCL8ugWL8gG6fxMI_tCsVvzfPoAf7emmCG9AWuIjpB_kmnY6-C1q2_wR9sx2ZnVtKvjM-AfQv9D0HPe0lHWtL6xixRzUL0YvsvqyQb8U0HxNOz-sSsbiBU2PCO_t-romZSrq_K3HcP-FlRH7tANNgr-GDJNOXsHDw04aNL_Fv4wHgP2cDVFzFKQn5qNu7whGyTr4j1abSFvNH49YovSr7jG2G9y6WTyUfslmfcsJr6ofU4yieidkPE_yZJJ8FLIqv9c9lMymRdU_gr5U7MOaHiq8pwyNztKe1T2ccrrVLTPP5_lHqiOIOdWfnkScthEjvgC8Z8Ov41Y4jW1YvvIHMQMMaKegvRa6k2byznZf8WXfk2bZ3sfsi7QqYOLhderiwkjKoZq3zxZjvo5wDws-0wPss7vGqzF30BO1OTzk8Br-1cBd3YJxilusotkrWDhfXBHjQhnxAQ5clFZC3G18WevvuBkkQuMG145WfLsyLqfrJH2e1C79LnszZU9H01oVZ3g_WB4yKWZImsuZeySPuvbLk3OIvbq3CsWhuf39mGIxDOoHkI5gokfYtbH6jnlVUPPjdnFcxHbCXdGvb4GwerhEVPPcN6Dz_eI2ntj1B9e3uTqMueUPSgtS9re4wT5mtn0WygO7PCA-o7d-9NBox5jc68JQ6RNHp8IBXpSeMbPD_E08MxZqi9Vb0TmsZr6TrWdljfzt8Oi138MBNV3pBzZedU3DtwqD3UXrM5hoXt1Dhv1e46xL-d17_swJ29uXue1ra6BRpHU05gposqreWJH9fuDkeVNPozreg039ptaiStlpb6ef9TjQdO6xlvm5bJ-tmzqZ9DzpK6vIddW6rUpl250B7or4Bd7_dhk0qu7ucnys14TOXEidKuGBRtoJz3ZQS5a9bmqXh_8znRWyk1S0xbX-rKW_3euqWr7LipczZM5NOmVQA7fg3escDPmRcfGxs9Vvcg1RNmLpBxZ2glx9KRcqdL-994_fvu-WH0_n-rE376n2tG374833t-Az27At27At27Av0G_4gb98hv0y2_QL79Bv_wG_fIb9Mtv0C-_Qb_8Bv3yG_TLb9Avu0G_7Ab9shv0y27QL721_g380xv0zW68T2_sL7tBv_TG_rIb8pHeot8N_qY35Ce78T69tb8b8pF-3V8Zf-5y71jb8rHajzs8te4NeZra9Hq8ZKJwbV37ds1d2LkbN_c58o-Pul5D_atDGYPmdO5mq1ONZZzY8Q6-O9J4mctqPJ5XeQ1yNZqn27qsYcrajn3qri90uzmnIWTfRl7DN2TyDEVZ_0Z8GgsZ0__vfJIjvvA58svnvT3eajvl_4zow5uOTnKPsUBu0jurQbnKWfRiT8SXHbyoD1Gdi0plD0KfKI1fiscBaFL5PbpnfvuO-mbN-Rafa43fI1rJ_TsF9WRnWtnDnJHe5fhbVHWqEDlQE5MKY97GC7GtNHF3wnx-bM6e5R542vaCI8QKvfMtH67OujHym7uYfH6Jb9Iv51mUl70X9OPz5Q834U2N_tn4qbpNPLg7uwuR9uII3Df1_sQODvumthcg1m76aYgHznXcAj8v-wLk24UxrnIlqn8Tz23gUZ9dsj88Q5Fn98r82P6ocqiUaj9Ex6rfmZXXNuXP6sDY0R1jx78_1ko7Y9XO2GJgbHZl7NC6-dWxshc8QS7Z8Ch9duZhm0PNP9ueLyvHJWfQf37q9Z9InuNWtuU5rFrOO9cy5q6fx2EnH2vzNqydlDDVnzIu16K4io8TmRd3Y3Zt91nLAde86PmxvZb1gRIW5tl5LUeY88Y7cw6dOVyjHOnho75vZFWu8bO0d7IX7su6cqf_FslcumMvnhfLvLGVMfRH5j7nTi8qyut82UuWRaunk6KmM_KOUxP3yucZ8rjO_Z59NrzCWB7_p-m3c-oXHas8Ros-eJ9f1Ifs6R6Nl2f_oB9EJ8xp7Ai942Uf6Fi9S8vebMWjpkfHQh53rxsehF5ju72gI29vWKvOrdRDnH1y7V3mSU4hz9fUz5C_tPKCHEer43zk7mfx2F639LJ1sWBabZ8PezuXe5Y9YxpjFtW5x0D2Xh_bc7RVT5v2lVtFnb9TL3fdnukp66KlvQms6lwm-_ScsdL2d0XVA6_hWdpvwJN5wS14q43bg8cCa8yGYGL_XZirzSv2YXfGWNX50c5aG-oB-v0x8jzADfw26x4stqG-lv11TPYbNBhbfRpoVvGq1Ge_Khoo5bmR7_fEyjMBl_B2Mrc9H0hvE6r7ZcqXs9TJ4es5Z4yv9aDMzce-1MdSLs90z49Nrv7R6Gqsfh4ScXLLszWyVyV0AZui9u4PWha2Orf0axnnml3U14evPjaZHHlrIwC3srEJO3nt-VbQg-Kp7lmZU7OmoLFNvo-8ed_g9OEazZ4_RbxsfMNBs2U8JPeklTWTsvYnz4XUsd6ZamBlzDEPJK0lPbxjWw_0U0_rndV-53qnDhCLtg6gL4uGPvumnpd6ToPn-yGZJHU8UPm58vzzxtIo5qU45Xn_SrXj3SFmb4idFKrplPI3Vg5P9dnmsGB0XpvsihGNCKfdbre0Z8sT4qrtM-D_zpxNvNPFfrl2Ed8KktNSj8tYpKyBjK2Cl2egENe-UN0zWsIXUY_Ki9byrHQW_da8kH268ViF3sCPz35vf85PrOmfX7ZM9sRKvVDTjn9-J9_Snsuxz_LcuIxXpb7CnpiZ7N-T_5GxllfqqDy3YPtmeYYUvNzWZ7wUOifDimWZczi20sYZalqtL2E_L8TxC7ysOudzCe_pG3iBO7YC1rENs9JmJ8gF6lpS6Yto7aKsl0qbnoun-vuRdc6KMK_6VHWsWeZxi-VnNTep1icdVHgds-p0Nptk8YF6R9QvKjo2Dzht_wGc6FuA2RBO-V04BVVufIGTeQ9OeeVf75OLQZzWfx-nyo9f4mTdgxPFDPkgTvk9OJHPtYZwKu7FSdZI_j5O6j-DE_9ncBrUp7txGv0pTqVNGhdiYdfnRVpdH5Sh67qOOAV2-jXt6_oVGbqu63QmtpDn-np64d6pF_T9mzXqxVF_QO8BnGS9fAgndhdO7jUZ0u_C6Q9sMn27hv3kX_g0iNN3fGJU998sv_iZ7Z1-Rvb-x1bSx4kN2uS7cdL_Pk5_4mcGcPoDPzOA05_4mSs43etnhnFigzbibpy0v4-TdY1P39jkAZyKa3z6zkZc4FRc5dN1m3wFp0E-3Y3TMJ_uxmmYT3fhtL7GJ-UenNj0Gp_W9-CkXOOTjDXuwmmQT3fjNMynu3Ea5NP9OA363PtwuhpHbO_BSb0WR3zncy9x2l6LRdV7cRrm0904DfPpbpwG-XQfTsE1PvF7cNKu8ukuW86v8Um7F6fhnOFunAZzhvtxGoz37sdpMN67Cyf9Wl2B3WXL3Wt1he9i2EucimsxrHsvTsNx-d04Dcawd-M0zKffwKmsSwUH3evXipKT371_NrKge3-Ie_cJfZfVnt-bh91aE49_dtf-cBftWM9ZKq7WjFW5Lt_9W9aBHZZTv7cLt3pfwfFiN-ne2__twhV7szrvaXf_X0PKApf0oO5dFbL_HYuTC1q58S54Nua50LZNzY7Hux8tDBZ6C97WUQ35vZeyKt-9ebH8dnlUfkfJfNmnrcaCDvX3YO9cG3_y8ltm6mUGoIPWnFPTdwr1dV50Cf8sNFs51DVhJ0uQP9e1W9-r6IX9B4e67gx5qd6n8nuVql_XORcZCOr71-dKNfB-sYxq-GLRzG_6eGKxbOvaC8h13SfSVL884ye_jfM9jVX72cnv-Sh_NBPvXNbo5fPkZT9RrMeuPLvl_4dwvLG7WGed-sPIou-fqX-6x9rGNm3fIV4N1or8BlbLUtH9vxHJzv9yZrTlQ7LTubNsZMyNgXd5LvGjd64wpv_90Onjxbv2-12j6dk139E252WT5an5vjJZBu11eZaiPAdM5x7pDLFX0lnG3mX9uOrlfsjcoKyrHxs6xnbuaj_r_7_z1j8rulMantd8zTv_v4B4k3S-DUyqeF2eV2Sd65NerZO_xLMapx_1mnjf9rGTSWi1Zx5D66lzfWzGt-cmk1PcrsNi0ev12rE8A9o9K7Fgsjdc1YfonDadz0jL_xUg7Xgm_SU9C3zZszjs7bjLN88Q8oxi_S2lu-jsedHu2dvTN3wPtYxA56UcVr3tZdq8gx7xLq6GPGtRn-cIenIY-wnX6FvPzlkMgynNt9eOqje80rLkYLRnlf_aK__zr_8Hh_B88m5JAAA,",
              "s": "eyJzZHMiOnsiZCI6MjcsImciOjUsImMiOjUwMDAwMDAwMCwiZXMiOjE3LCJjcyI6MCwicHMiOjEwLCJrcyI6MCwiZnMiOjAsImRzIjowLCJsIjoicGlyYXRlMTAiLCJ1cHMiOjEwLCJ1YXMiOjAsInBsIjoxMH19",
              "sk": "{{sk}}",
              "ui": "{{ui}}"
            }
        """.trimIndent()
        )

        onFailure { false }
    }

    packet {
        i = "V900"

        parse(
            """
            {
              "pi": "{{pi}}",
              "pl": [
                {
                  "i": 1001,
                  "q": 1
                },
                {
                  "i": 1002,
                  "q": 1
                },
                {
                  "i": 1003,
                  "q": 1
                },
                {
                  "i": 1004,
                  "q": 1
                }
              ],
              "sk": "{{sk}}",
              "ui": "{{ui}}"
            }

        """.trimIndent()
        )

        onFailure { false }
    }

    V303(10868)

    packet {
        i = "V876"

        parse(
            """
            {
              "code": "$code",
              "pi": "{{pi}}",
              "sk": "{{sk}}",
              "star": "80",
              "ui": "{{ui}}"
            }

        """.trimIndent()
        )

        onFailure { false }
        onSuccess { true }
    }
}

fun 刷邀请码_苹果(code: String) = buildStrategy {
    version = 1
    description = "苹果 - 刷邀请码"

    packet {
        i = "V216"

        parse(
            """
            {
              "b": "0",
              "pi": "{{pi}}",
              "sk": "{{sk}}",
              "ui": "{{ui}}"
            }

        """.trimIndent()
        )
    }

    packet {
        i = "V205"

        repeat = 2
        parse(
            """
            {
              "m": "b972f6c4ee55a944c86695ab3c900831",
              "pi": "{{pi}}",
              "pr": "H4sIAAAAAAAAE929W3viutIg_Ie-Cx9gzcolaQ6BaYuBmIN9F-xuwNh0nkWnbevXf1Ulny054O69Zr9zkSeA5VKdVZJKpW_pgrtf5n99Sxepl87_suyNvhx7A8seacvX-Hw4z_-ah4nOdlbsVv_bE83SE31pnwL3dX6bR9Z5GVgDazyHd1cGg3d94yefBz8Sa-xoS9uBNm7kQB_zwPqwXgf618AzWIrvJrqzf75A2_P8HJ_dGcJyEgs--_vFxzx4P8Dn1PoSc_hL4M_YI27R1vQiFtT7GGredcHxHWzjv6xTh-gaGWx8Mb5-WWge4TqJWbAaMuwjEm12unabX92LayDM9wPw5ALwLwQ_sKD9RIP38bcb_AZ0TmJrfHr6-nI7uy-LAPlo2XMOuCRWGtNvB_iNBR7wdBVbL9pNAjOtwlzac6DlOPj-mvyvOeJlrBDX1OIjwxp7N_FbTPxhCHP8LOl_kyL_8_6XYye2bCeWwMS-KjBBdoGTsjbM1Bpf4uWXCk1jT7P4xFi2aJrHy_FkUKEpAW7C-xOj1X8wAh5WadrobLwZLsdrSf_HBGWV9w-8BzpBfldp_3qlf9DVkQ56qcn7X1X6R524xEzGK2prVdsir-A3V4qrVeE_sz3OFDRZFZ5a_DiE30FPdF_oKYtzPT7s23pc1Tf2BdssGragl3oGvHOiaQrwImeXcBdgwn8NnscA64c3o76KZ64JeM7m52Xkng4vLIQ2V-Qx4kl2Ep1S76X2nB9esP8KjBcWH2aLS2ZLZ383vDjX9TvJZDwieTrX7Yc7e6LfgMcG6pj4bYG4JQCX3nP3JAdjOb5wyz4azA6Jn3nbt1fkM9rDBHBb6Ido_Uv8Nq_-dvaRZ695H1tN6CfwnU8GLNgMhdyztrML6qRmjUdpjsfBoN9AdqNB_ptzRdysvI-Q-rAdsGUrAdvTl3vS0Sb9MfqIBv26db6fftQLb78NHfRn4U_E--Lt2I1klC5C1wxjf-ydsZ0TrRPHgHbnxa_DxX0_RCF_-4K-d3HxZ-Elx4mZpHuJv9uC_Agufr_C8xR8WAq2jD7w6qIu8hXy5S8aN0BuoB_XmvyjBeoU-OMt6kfkXJ_Ple8fvvms1fVx3fqNYHMnBl83oD5e1tyr2wHaOIwZ0_SNT87w3Ty85r7c_-nuVoI3Vw_5B_RuMl6hHNFGjvBf-DvUF9SNN5Sf8GsoF6TxB9qJ0I1LJuv6e2h_4j3iX2lnQvZmBifMbOE9ky3CTUq4E0lf86yvadnXWIJ3mvW_L_BOWJsWzfrSame28XDafb623uOS35KSD8Vvgzb8Sws-yLPdZxvXVIJ_ijbT-G1QvquUhxhPUFfMHIeJWh8qNBb9vVR-k_E_bbeT0ER4tNpJ-l1K3l2228n4kcp4KekjZbJ327Slkn65RDZcwj8uwY9L-MclOMt0jktw5hKcuYR_XEKHzEa0kg6lPhlN-5b4EonNW3rb5leSds5Q0i5tt1txyW-a5DdD8psp6WMg-U2CyyaW-EkJvE0i-U3i9zZc8psm-U3Cv43R_s2R4OdIcHEkuDgSXBwJLo4EF0eGS8mXLY5TZRxFbcRYldIcouqffntsKt6T4dl-r-2_Bvfgjf1V8V62-5fw_T-Bt9r3W_alEQtIfH_bL0v0YNXuvy0niQ54rfeWn8cQOL_5L8a7eE_iHyYS-5tI9EAWA00k-E8k9jeR6MdEQsPkLvujOXS3_f1f1wfW9BGPxjDV32TxiizGk8UrkvinM16p-DBpTHdnXCOJGx6PdSq4SGMdCbz_9-KfDh1r2sGfjpNl-iQZMyR2JI2xZeONVO9kv8l0og2PS35LJfilEvxSCS5y3ZH4cYmNckm_XMJTLsGFS3jFJfiVMXH1N1m78-f6ZAUj5RhGaxrn-V9uNL15xpb0y0_F_Laqg2w8uk8H1TFT0h3LWffMR3UJLMncVhLbtO3gznmyZO4vmzvTum7ztzY8JnvXlqw52BJ-2CvJbxvJb5L1BNuT_CbB2ZbgHEhwLnSo-puEjopclbpmz1tzuf--OHvVsKG5LM6R6E9HvFi1G1m7ts5K5pWy-E2iE-0-JetQbX2QjLvSuEXiTwcS-x1IxtOBBLeBzF4l4-lAgt9QgstQ4quGEjkMJTwfSugYSugYSugYyuK0qv_osAnzv98mJhSryWP2rrng5n_WnEpJf5OOlWyO1PYJMn1Xz3WS7vnbKm63k_h6if1LYhbJ-GpJfuvyEx1y59bncm_roWwNqi33Ni2StbBj8Z7a7iztv9_uvCYfZfP6Nm_5Rkk_fQc8dnwSfx3PbyCH5Ot4wr-OQafHc_w8_Dp2bmxs3SyIG0DeGOvhvAHjYYyddWgH78L8f2wN4A_aW_D-PIU_hAPPLXi-ge_AY9wzHoPujlfwfQXwN_gHv2_g9w3AcLAtwNmY8Id9w_MV_Pfgdw_gOfC7A-08-N0DGB48OybYB-CD4wLOI6DN6oZ73uBPbxbojcXhc4B_R_i73Jbgi8EfI744F0H_zy3gncWRFgvnOxzXrpYEY2RSO2qDsObw3iQh-hEmnyPtON_gFkcYSK-FvEkIpk28wbkMPIf-OdJs6cRDiLEsvqL2-D4bY3uCj3PahPqzCb7G6Dm-vzLp_TO-78B3h-P3JcGb43eUHbyzwd-Rf8gz4NXxhnEoxmYMfCgDXjDgBQP9YBy_43-QNfBqCXH_EvWBj_A9gHPUkMeUG4K6wEcA_wgyOwLsI-KHeQs4VnPMn1jayIcR6kJC76AcOOjr-KLTb2mM4-YQ2g9Jp8ZIJ8KciD4g5sW9YpIFPUe-jDg9R76ifPkc9SkR3y_I55i-gw5jXAV_9Jzh9wD1AeFNUsptIT5ZiIuO_WHcDXJJia8kR9Idk-RA7S832odFOVB7lLtDciS5Ie_GI-Q18oEvER_uIXyT2iNfcX8b6TnHmjVGuV2QPxgHcfSR0B6fmxbtqToYa6Iv4418hBTttbovezCZ8ba3zsvrOvV3YOtX9uHvt--YtzAPwyWbsjcGbPlqr_e78ebnlm-t11dN301BP7bTF0tb2GzqTtjkacVor9qPKBfoS4z43IB_htAfsl-UwQ1oJDqELaO9O7qwfaQBfcYEeIf8QxtHffbgD-WFPEQ5oJ2hf0Cbng-Fr7FMobcr1FvUhcxfoF_xEpFnNEK-4NgX-i_PJ8IzWIs8jtkAnmGMtdUO1-wZ2DDOg8kOUeag-6QLaFdoOxWfAPp6E_ZLuoHyRp1AOQv_QbZ7QTne0E4Z2Af6RQa6yMYof5Sxd0MbxTkg2RroIc7zUL9wHofzNpynkb6ADqG9CnuEdgG2Rxu9ZPbpVWz0GWn8oJiLI1-Rp8g39DXII3j_HP8En4GfTeTVm_kDxopbkvl19LHYZiDkg_z2hD9PKdeL7IThf_gN5GvCMxP-C3sBuOjr4TP6LZ2-CxtO0B8sEWdb0P-VbH-SlrZf5f2GaCz4jrxG3pNMnEwGFb7baDMT9O0p-jrBowv5J5IVxkGgV4BLijKwcI8LvgOOKflS3GvD52f4jj4F9-PgO40ThBf6SPj-hXyQTn3Z1K-e_WaK3y7kn4gXNCZhDhOOiSPhY8Tv4v2AfJdR8V1Eg_BRI-SJRnwe0xhkCpieoDOgNugzcA6h0ZhCeI9ighHQOynJTIzPMfHWxufzAcmDbwSM19wvjgS-9A6NoVzY8yQmGole1CEHdGNlZOMftjWFXSOPccydo39HXuFYlAie4Fg90bBfRj4ZZTsSPOPks8kf4tqMhXuDYN8WyZLkndJ3lB2NowhP-AuUN_pQRn4Y44MJ8Z5sDn2pjbLGMWk-QF6g_oHuIPyhGKOwbwtpxHE1IX8d4Hg0QT3WLCEXlH1MOm4TD1GOqEuJ0GvSA4P8c0oxAvJcFzwXuinkvhKywz4pzhB6gOMg4E72SfwBfVui78T_6AvQLu0LxU84hi3RLuwL8g7tKsX5IuCMNMZClschyUzELsNs3EvEbyvxG-F4JP8gZIi-9Cj0EukJjhmum2y8xP5RnylmwFwpzCEEHqOPGwENYkyzRB6VRv_Jz9EzLX8G-Iln6MfEszR7RvpOz9AHimdx_t4yfzZGm_Yo5lhSrAl8J_xRJycGyQRlbjtizM_wJzlRHDcRMYGd6blNsqY5N-qg4BWOQaiXqGdkbybxK6AYb0A8p3EZ_aOF69ZcjB0O-ktT6O0J_bDpGbcs38n_gPj7_GY8fbiGq2Ne7Rvmf-VzfMzdo3h9gDlh8cH0Q--6jf3ZNPBnYQBzpcDf_QzpvddiPkAw_f3z1YumF3f7t7E3GYzza97d7om_zZ4Md5d8tyhX-FLMAyi_GPp1zcXJnW2_M_2JH2ZPmpfK2jHN2S-0t93wCv3qh_DvRNavZ65P3nX9foj87565_dgDdsCb93I-ktE8m374M_fXwVh_d_cnDXD97hnJL38qh1uhe_Aw3WcZPf7pMEsgJoL-ozBFvN92KxmuHOSSglw-XOCPvxt-t6T8KfnoGadffrS97SHavweeiK-a8J5vzm6oudHTL3f6FH7DXOqtkI8Cru7N_NTZrUPgz4-D6YaH6d9aySOR1_ptB_1cYX5r6JS7d4iePkQOecIxd_IrrT0Os88wp6dc3ktNr9cgO9fYphvA7UDvilxjWmubxWK-eX2OHOPvLPainGlOces5zw9cU3465X8GjmmNKTclplxchIM2kseu0elnPbd1UeTmvmG-epkHG3jGRvQJ8DEuErCoL1obecO8SsKJ8UP23J2FMeY1ivkvxsqUcxkL2odxNv9_PZg43wZ6L-zX4WX7E-WE79uzLehZLNYiQiG31x3ljCY0lgv9zN5f_eb7jf5fq--vZO8PshxYef-vj_Y_qr-f_mb_D79v1d5nXz59n1ummn-sRv-k-T6MiWI9CHTdzHRifDAS_bAbiDWcSxKirWdrUyvya5NQ5H6PPWGf4eKXn-Xproynm7_TP7K1SMvdg51-EXLYzJLT4bpeiNzykcZqdE_qeJ8fwjvtwDtp473Sv291sQ5mkG-J3D3T3vZgZ9OnG-E8BduPNuiHgsJm77F9PhXzMpq_OgbGRUsx_z579P6I8tsruco_0E4L24zWNNfN8uBrOe-OycJMRlrhB6KTludIv81OuR8AP6Flz9fv3kvNh6Ru1h78zIfoq8aL8G3n_wD_HYpxknwf-ZZcjt7MDQ4ZDHEGxcL597k8F0NnYPCkiwHzHrEnC_4-h4Nrcf7LQnfJv2lV3C7edZTnhf9wqj4L_Fnu7_yXgg8__MLfnX7kfAA-_cz929v-mddyviMv50t6yPoSa43C94Ov_Dj8Nt0QR3FHw5j9fwrdMO7CuM3e_ZfL4zpfG-8cHeLtIcwnh__N490-W6unWOSyPkE8F3rgxw6GvsGYQpzDIf7mPiR2I_Yrg4O_YXx-9ox5Lrfc5umcFcT1lDvxP8TmA4jRzwezh-xxvgtzxar-wxwW5gwbg3IC_pv1H_ccommK8SPowqmCA3x-LnQS5gWx95LbDOCxq-F-O-x_IN0fVj7P-5LlBdlHnXFLwzUc4EMq9sgvA3Fmi3J3jTxG8mZMy-zHAB1KYb6GOvfTjVa1vjyaZ000OpP1so7E2R1vgPZG57VSso8QeU17UEAHfsY9S28m9k_ozNL1WZznshnpQflsnj8bZuNznO3t05kij85PXsS4f33O93gxNohRbkv7uQFvU_TFgmZfTonHOGw88yo4NmEeCxyz2K3AcfmqwpFyRONsXbn5LCmfrdLGs7SkbVrHw7ZyPNL8rGT5bKXCMWEFjpO49ezc8azg_0Sz6rKhvDTxbJ4um89Knhh12uaxUm5BRUeatAXzUqatZ5ZKfyjXLcM_yWLW8plSbpR7ImQTjAaNZ6Ya_2OJo-03nl1UsuFWFcfmM7XcKJ-ukE3zWap-r8aTfeNZR3-lnjT5ZZlqPbeGKn5RzptcblpJN_iXl8azc-VZ873SXyR1PFYVf7Fu4LGp2FTzWdUnbBvPlDLVO2Sql7Qdh3UcN2mFjw2fQOdHMp2cN3i8UfpCyl8r8HcbzwpfmLLxtPHMK3kSNJ8dVXLTK3bf4L-jtHtW2n0TnlHR8ab9GuqxwdHUvKIzLQo8nFKeDTxKP3jRG_AGBbzs7HwJ71LxZ_VxCNd9877q8LykxG_ReGeuws-s2HtqXevPKvYeL18az0p5NfEwlHziFd2ww8azQjca-usN1TI5KnVjWcYNTZoHpW5ceAMeV42fy7FyjBxUfF_T5wyY2tcOynGwqYfHkmbbaujNRU2zPVHhOKzYQ9P3DdX2cKnYw6Qxnl30EsdJQ2YX5Vi3LH3AoDH2D5fVMaRuswntkanGT5tyDnIazGa8hPufqvimMx4U-xdFrCJ5d9D1bknPcdDCyR6p9LMW40ngJpZyPK3FeU2fmFC-RRk7Ju13C58_XLZonRR2zVqymahjm1q8t2g_U8fA-VqMQm7zLh6lFR616Szjvna8WI_9eJvWuXIcoHe7ZBN4HTKfDzplU40DW-9a6ti4FgtKcOId8XEtHmzG4_huxSdeWzh18akSEzZjGYTrdeKk9pv4bhefVrHKvzdiR4mtrzrmVrX4sYUTU48dCLfiR1u0ahV90ttwvS64Xb5JU8dbGKeNuuxD79InOjNRxI0tPukln9rPSru7aG24mw5aN4YqlqLY8ksHraWP4e1nyvEU47dKzO218a34mPazVRkHBS3-Gqyih835Ma7Tl3IN2-926WGgXItAuJX5VouHZmVe0obLu_jkpR16WIk_m3Ekwu2yG6_Lv5gK__KzY36IMCs-eNS2t4pvYS1_d-wajwZdY3Y1tmyP2ccuOgdd482yOgdt0Xrs8g-DWizWolUdg1I8WfqHalyItaNEnorcf-fPk_J5TXb58_ST9yvrIg0_HsxFLoxq_SB_rvR_WKuK8msU9OfPvU-eHyvre5aEholWoUH2vLJOIIFfmTPX4hdBX7Icj-R6IZ6nleeSvil_K-ubtfvmlbXLNmzN4l2wV1oXXazKtzZs3arSddXF2rg5yvJ7poH3sqLcjEMUYl2n7260xdyP29v-Hdegb9Sm0rezYzHmXsyv24-3_VR3t08nx2DhYbZNnf2acoKwDfFZ4HNyqG7TD6xV9Y41qfbmInSN7Ye7X5ygr1-HWj8nVT_awVwEb1-qbZmqLT9ET6YbJac3g_aAEYeSvzK6X7bBW1qFva7Djrbx24518GeSNGCH3iwMummdSvtwjOSXY9yqeKdS2MZ24M2SX-6sRqMCj_UvL2I_qvLJfFghH3f__O7oSt7JcTCffx3q_Q8b7X46Ox_hRi7lZyXv34CHNTz2dTwOu-mVeP0C8nntkHekn1zjMx7PmzLP8ZHo00kBn30cTD86zKb_uDV8For2LZ40eQfwEI-kQ5-aujEE-emY33DxrtufXfzL5BgcTKbV7LHRzjO3MfAs07ef39lrR_9Xpntg43uD3Q4G-2fPvZrONXWpCRvGwGp73uAH9_fPoRcCzrOn4E3_O6njsv0El4lRp1Oc63KA_rJ25yLwzPUt3xP0XtjNyc8CwnvlWelQ7J9mZ88A3_xc-LvYUxZ-oXgH9-t2lGuQ1ZAMg2zcL2BlfjCHlajfoXV1eT8d8Og8mxSeo3pH68B7oMJB3Q-tzcjxpnPgUhxEbQIpPKvC72kNnvod2gtT4KDkg6hxIOAlDRyGSnh0NjDTE7OuJ3ReUo4fV8GjPRMFfpYSv41aJ7mS54mafysl_zDnW6lHr0r8lDrB1DqhMzU8QwkvGCnhqeW7MdX4XVR-wLDU_FPqC-1PyOGZajt01Pip7dpU4-cp9YXmcHL8Bmp4dHZR8eyotp1AKXs1vV-Ufkf5jmUrdTbt8DtqeHSOVQ5vqcRvPlT5saV6rMnqLiv8QTBX-Z5hRZeaviztkGNqKfG3RA0dxTP1eHDpkMtI_YyrfSQ80zueVcasbaO_ia6SgcXnXbgo_Q086xob1GMht9RjFLfU_lys6yueTTrwtNTxBLeU4zyd9VU_64DpdNCnHl8Alw5-rjpon6tpsKtyaOrEXK0TdlXuTX1ZdejgqoOfqw49U49rwM-uZx22sunAxemIG5yO-FM55ujqMUxtC7ROL4dnqGOoo8r36WofpuYTq45FjXFe7dOdjnFeGceZ6jHMUeoU5R-r4Cl9_VFpZ0t1HMct9bOBWr7HDl8w6rD3UYfujpQ6v6zEyQ35D9Tj1LHDhkbquU7XGKzW3WFXvKQeL62kawxW24OVqPu7dPiqiZL_S_Vcaai2iYvaf9sjNR52Vdas-SxWPuMTTe2j1fNXeNaFS8c406Gv9ijtoKHDPubqZyJnVdVfB68nHXNgtcyz3AoFDZMuPLvi7673Osbtrnn8vGPssjr0fdPhizYVvmybzzr623T01zGPsDtiTrsjPrS7Ys5V11pAhw6q58BUJ0RpY5sOXDrGs_FIqWdsXPXFiwbMo3IeRfVK1M86_ISnjPPYeKRcf6IaCer3OmLHSwcNx473LN5BQweeXu09OgdhTDWX7jdyaE8Q12N9PBM1m2T7QXS-zsjOzZ-9aPrxhuvL8O6hOMuXy3SVw9ForXU3_GcuztncijODWUzl0lkr3MuiszynQ3Q7i_XR8oxfNp4U_TNl_4tG_xTLy_sX-10_vZl_y87IXt3dMBTvetwS-nirnnEU6894lnwdlvtFfniIRG0aBnM4ESvVzifSOvTGXAM_8YxPdkcS7knsiCYT4yFWo4Pox7Vx25-BXF6L_bsGjuRjH8cx6IVj2g_HeT8caV3sYRx5PxytXjiy8aQPjpSj9DiOtIb4OI40j3kYR5rjPI6j18tmaP_8cRxp7vE4jpSLUeJ4luBIZ8f0GPrLfQPhusSaYLQ2N9TpPKKoS_CXZySnvJ1krd9Qr_W7jbVl5Rw2rcxhmvAGKniUq5jH69fGuuSrEp6mhFed518b854vKnjV2KsVA-tq3FcqXoga1aIv3sS9gofeevba8eysfsbUsqzulbWeMXV_ehcurIOGDj3Qlh0wl0qYWAtKuTaBZ_zUc2SsKaXe94s79l5EzamOuV_HHmTcseafdDyLRe1oqU6JWldqPTUqcHlznq3WD6u6p2Y08antQ9Tt6afV-d5I9V5Vd5rvaWobxRpCSpiilpfKToE3al-CcFcquEO1Llf3y5p-YdLhn6rrAi08KzAXzWemUvZBF1-OymcVnqSNNT31Xpt6DdOo6FFD_1ZKmqt7gc3xZKnGfajW55V6rFHbgFpXlX581WGLGzUO9katN_bKUMt_pdap2h5K872ONSOu5G9aWyNsjqO28lmSxVcq_Y6X6jE46RinjI7xzVD7jLmh9lFzQ-2H5kZHbGR04ql8ZnXJXc2zTr-l1Nu0utbcfnZRjnm13IaGbFmHn6nlWNSfDTp8Ou_wy3pHHBF3vDfsgtnhA5QyEHMouc_rigHE3RgqO1GPEZ35Jcq19MreQoP_St-gzslS-pOKTNImvWofZKlwSzrgaWp4SlqrcU4LP0s5PlH9Y_WzrjhPKY-LeixX262p3qOornE3-aHc6zHVY7yoa6qOfbv27Edpp94HXbF6xxo41vZU8hPhqvPCqB6oGq7WMQ4o53dMPQZCf9V4rDXGGx2xoTpO5R37H1ydo7hU-6fqfl5D_lV-NfXTU9lXdS7Z1KdhxzyiI46exx3zgY44b94RS8xjNS5U91U5plbHxvb866Le08M6sB3-ZWkrcy4RrtYBt8rzBtyjMgZlXKm7MP4r10jQjtR7msGRdzxT7iOyaq5YM-9BGV9v1Hstal52zPUdpexYoB7fK7qZtvRIqdOTYYduJh2xZaz2U0U9YMUaSfb8k_c75k3iuRLvouZwV_-p2gYy_JRrWEXd4u73P3m-7O4_UduSej90OVauyQw6cn3UtmJ35D7ZHfuydkd-kz3pyGfNal2pxs6unMas5oEabseYXNvPb-ZAdOVHdOYyVHWoaZPVPJqGfD31mBxc1LnjXOm7UkvtR7lVycFuPavG3a1nyjlWuqzyWuwNxs5e3BtB-6O78MON_qb6w95lqpEMMM9ZxNJDn-rpbcxsT11zzPAmzjMeOcnxur46RiJqReDdLljzbrZ-P4hzmQPcF0I47n79vjKe9MN1vXL3i4mzD3fwn7_tnj7EGOJolIdzDUN_Fo4P5vbDnz6H3mV9-kb-dqNblBs2FXy157jWQzUGv-22GvK9rJ3IYn-WiDM6IN8lnt3EOnQvzwHW3RP1lH_-RTVb7ctQnO9eXP28rt3V_yf__Pbi_6L7Kc7ZvQuvVHMe78vBOxp05KE7S5BvtP9ymOF-DO373BBvqrt5ZSnus-R1BPG7g_s2RW3J6c1_eb5kMDQBg2pOXt1aDcrkVq1XuLRXXOga_J7VPzy8LHijpqEu2pwuSHuOg_vCUvxOZ4H26_Cwof0njWrtf1mcDjuUlahbiz4yswvN3emiFm71DG00_UnyquQU-rPtT1H3FveOj7U2eX2DSr947pPmdc5O_-XvhqSHzT7ZWN5nVqOk0ucqm0dX-8S9eEm_oJ9Uh7GjX_Q7y0Ybq9Unnk2lfesK76YyOsX51zo8vU2rGHdqfeJY1u4X7TD-pF_c9x805SqVKa3TVfh2bfE2O7vc7HPb7FOiS3OFLlktmVr36BGX6hHFK330qK27Ej0KOvTo9WE94nI9opyaLnlqCj0y7tAj3qFHyeN6tFH4BqenHrl36JEl9Ij2s09XD-u7hrf4K97T8iVu_UcffXh5Tg-7lQ7wznRu0mTvLtWRnafuOAxhrmW44-mZ2W7kBmHEZg53do7h2KczC_AsvHty8awqjA1e-a5pjefGcucMnAgg8pVpRYvIjTaD5WwFftfjy5kTu_YpEGc7fax7e4a5lGbtJqkTHAeWMb040URju43GxosT488Xi29PTrQIwBbx_Cj3jPBKNX8DH575Z2u2SeBPX463ZzdawzsrGDs9w6I7MzDvYEVnYL3rM4yvolayw7cXazy9uNEkdjj0h3YSbWLHfj4zoNuN5injYWRxOsN7q_QZOlgf2nDD5XgdOvb67AanyB07oPMr7tjWwIq2AbMhpsAYy7SQLwbQA_EMCxj05ezcyxLoA70YLmeToYVz9LF7xnwt6wvViaGxaB4sAA-gyZ4PXfs5xPMMVnAKXFzj2UH7nRW7O6B9NxkSfS_5e6MhSGrgGNYQ8wJde8OdwA3Z-AS8OZ0d2-NugHK9cPEeu4n35qnDRyCriQnPMMYZOMH6gjl0SxvGTm6l7s4NgD8nfM-n-rw_AL9LbAHfob_UnS0Cx1gADyemE6xMNnZMkOmJRYC7Qfds6K7h0nuW7Qcu3oNjTDjIwYAYKnHsi2bZC-AP8AVk4QajARvTnRemi3eFnReBG2B9Zg_r_GjQx8CxN4DfZoD5mMDhgGpXj4FC4uX2JGg7Dhz-HADtOryLd6kMHaCfdHTMTq4dnpYzkIkteOLktCH9gWe642fodwG6ur0A_3Vnt46AbsMC-8C8U8ce0dlmzxTvOQbVkAJ-nkIWTUOHW8Zytg6Z4RjIW8fYRmw3Bxwceu9N0AY66ekuyjgC_xNsT2jXLugYmwEeNvkI0BsncVLSk5Mv9AthXMQ5aYp_frkz8hVk2_4u-fVmPue5KzHEuLHIk6Hz1UbtbDnxzD3RO6mohU_-DPp62z9Tfgz59b0rzulHT-BHpjfqJ5rqh9nTPxQfZrX5vdn03RO4iFzDa_Xse4x4G85-ezvQvTS3JKvpXcFx0vM9px9tFAv3eI_OBvZ4j86LPP4e6ycDOufX4z3aE-j1Xk9dWfalrx-etBbf671-eFLtv17v9eQL69ffsJ_t9bSFoJ8tiPoCfWyvH549eSnmcz1soWd_g36yW_X0t7Qe0ue9tOd7er_36Gxnn3GoJ560J96HvrgnfX3lbvZ7b9OTvk1Pfm566uemp35ueurLpi8_e8rP6akvTk_5OX3ttueYsulLX0998Xr25_WNV3vqmdNTz5y-etZTfk5P-_N66qfXUz-dnuOK15e-vv311Bevp9yPPe3h2FN-x77jbdxzDjDoOQcY9IzPuCXWW3vEraOePubYU9eOPXXt2NM3HfvqaE_fdOmp25eeui3qJvXTmZ7rGnzUUxaiZkE_XI89cZ30lIeohdBnnttTHsOe6yLDnnPBYc91g2FvGdo91wh533U7K-ntT-2e6wfiPHc_XHvO6UW-aS9ce_o4q_faH90112tdpu84Jc5-9cO17xrSqOeYY_VeAxTnQ_rowLxvbNR3nZOLvOFefO2pr5Okt7729Vl2X5816e-z-q558r7rWFba22cFfX3WvK_P4j11gFu8p88SNfj66UDPPZIsx6SPvvb0WZO--wji7qFeuM57xsqTvns6SW-fJeqt9NLX3n6g5z4g4Np7PbunH0h6-wG7rx-Y9PUDyW_4gb7xQN-9S953Pyqrq9kHV63vXr44q9gL157rZJbWd87MesdZq746oPcdt1jf_APedz_F0vvGhKz3vuum5zqUpfccYznrO8byvvsxlt7XD7C-YyzvuydjGb11oPcY23cfoffakNnbf_C-a2737gnQOYQb9EnnJcQ97_A5zzegO2DdE-V3VWmD53l-h4CJ92oADfapwAWeFzJGfQRa6X6MHX-WtBExQdlmk0r6ErFKiQ-3pgXPCpwp9irbpEtZG1HTr8R7K2tDazPdbeheqqKvWMafOl2TgZQuukuygCNrQ-uLJRwZn2uySuU00Z5PSZOUN55xRxvtszbi7ttO3eHibtSyDZPyeF6Xlb2Q6Zhe57OlyfoTdxZ-oj-iBnDZX-DL9HX4KY-C0acyZecqzqGkn3nNvpZSmdJaV4U_Mvuy4oYe6hJ8hp_j49TlrsvkNam1YaGMN3XdkPGP6mN84nusOk2yNskdulznXyDVL6op0m1_otZABR-ZP-DiztlunRf3BHbSPvxUDmKd5pM2lLvxiTznd_jBud5sQ7n-Mzf2ovA9r3-JNe3EubILjkVXPD82v1TGtWgI7UVtERZgXvUlEeed3ez3eXbP6kUX94Qu8t-h_XGwBF-RnfNT9HU6eRHldH94LwtxJp4f4d3LkO5JLfqZxGyMNSKPGt3HUvQzSbPzcTy_L901NBxj6VwdjukunkM8Lyx3hzUIa30lMJ5ruM6TnZ-M8t9B3ubSPpqWuAMkLX-faPBMs8ab7Oym_5Nyw41pCn8f_sv2JO7twhqjP852JbY4XJ_TvHYv4xO6J8TNfmNYB2d8HAAPtUp_Boz3JvARdHRdpe3q7OFvN9T83TSt0DiH3y_1vjwD7xdlfE73mxf9cczH3-C5Ib3SH65BDpd4biE7a_JJf1KeoizAVyTibF0uP0djgWPiHap092WB32VIufr2Ss_OGAk9idahH4W3Sl_LSsyU9zXAuflyfNFYTScvKcgI76gUd5OXfWE9AuCxld_drepLwke6kzZmfEP7PKXcRqAjK9BJqvGY89G0xhMD6_jm56EyPl7cvfsu7CrTj9kWa1U2-prrWIuZ4ZmJio7gOYcl3gcr6tTnfYGNObplr_iy3pfpgt75s-Nndo13L8M4fMnOtxX2pkEsYFhYgwnndqVdgx1uUhgvgOenT_pr2TbWPEJ7A34dq_aW0n2e442R3SFd6CMje3fA7vxP-krEnX812lZDiOOAZyO63zvnI8QBOAeE3ymXIe8rBR3C_sEGKnTRnEZty8ymMxjDrF5K4T-YbWEfA9qjL32VAbwDffFAvvI-XmfTtE4D8tvjIPeYYtJCPuBbQQ5LxPlLVcfn4LfQ9sBnXEsdJz1S2yz6B5CpF1OMWPYBvmCFd0dzq2ZHlon-D_vPairDfGV4Kucr8Dkff8cUV3w4VMMVxiGOd6_iXzFHyp6J8-1US7nWHu_LhfaBor2IH5vtgf-K9oEUfqqEL2KeZnuY96jaS_HhKnzE-epWe4P4g2f4sP3-OR-v4wPEMnlOMJ5pgz-6U_mQxTj5na_tdqt6u6uinThjU7bbK9qJWuNd7TJ5Huv9mvJ2aOP3wGPBp_RSu6Wom9fVb3YP8KjWjin4ArHHXfhZ48tn7QT_7M197fjlPrmJ8eeOfkd36sHxM_lm7eZ30bEc39eupS_lekhY-pftqdw3p9r8MYwHuc8BuS6yeLjabtNqx2xpu6TdbtpuR7KmO8lDZzfN51m4J5bfcx865fwL4NIeQhUu7k3zLA7B38u97qwmB_aX-QouYICf2rfao-008JjruR434GZ3YhdwkQ6g71lKHxP3UDfo8-I2faLOTQ2ulA-jpN1uImtHOXENeLoMXoue_Ox-Ax7dX1CHx6X4nVvwDEk7WkOqt5ubsnZt_OYy_NI2fnMZfrH12tALPMOOfxK9ELFHTS9iiV6IOnD1dilr62VstfRhnp_Rr7ej-VmtXbKU4Yf5Ds1-Ze14Cx6XwWMte5xrcjq8Frz8Dvg6fscWfhJ7jemcaINeuTxadMRyvrTgSe1exDKNdlL5tvpNpf3yljy4XB5WWw9k_LNb8BI5vJaecqmejltyi6Vy4y094DI9oBoXDX2R6TNr96vJ-mVt-WpSPbVb_erSfu0WHbqUjjafdRmfWdsudSl-QQs_Q4pfW08N6fjU1lNDpqcsaOmVIdOr9rg0N-X4tfhnSPnXtnNDZudUW7sJT0pHi8-GlM9tezPl9tHiH5fyr633ppTetr2ZUjradm7K5dHCz5Tht2zb20AmN6q_2Wwn4QvdX9hsJ-23RcdARgfVeG60k8lj2R5nBjI-U731RjuZXi3tVruhtF17PB_I5LZs-6GBTP-W7XFrKOVz218NpXKzW3weSvlst-Q2lMqt7f-GUj63_d9QKre2_xtK-JJI4iyKk62xah7QpHuiiC8xhhfz68Y8IJHwKcnru7fxOCnwaNJHeAwVeMRSPOTzkaQVjyMe9laOR0uvEI9JosDDkOGhmL8krbib8PDleLTGsYki_kY85jK5pAq5pFJ-BAp-tMYNwkMyryA8TBkeknhDrCvJ9IOv5Xi0xhvEw5LMq2i-KpOLLC4jPJYyfvBQPq9sjQOIx0ohl9Z8PKF4SW63usxemC23F9byQ4jHJlXgocnwkPgjwqM1jyQ8XDkerfgI8XAUeBxleMjGH8RjILOXfC29iUd7fEE8jgo9pXyNFh4Kf2q25rfID84U_JDh4SjwcGTrKrL4LcnvrWnhESj0tBUn0fqHAg9PhoepwGMoG1-W7XWmrA5cw59yUaPPaq_bZGua0vaJpH3Gb5k9OrJ1irZ9BZuBpJ3RXr9x5Os37fUWid6L_Zt6O0synoja3vV2K9k6itFev3Fk6zdmu19P1u-wDe8igUfjncxuZOsjYl9COs4o4pCmHyn3NhTjgWyclvGVxgNZvCBb_6E-peOBws7a8xvCQ-H_Vs113ITm7fJxqa0PaO9jhd-RrueuFOu5K9n4qCnGR03mh5nCD7PWvIHwkNgZ4SHTJ9m6gxiXZP5PEce15vfl3pTCX8r0SWYvNH7o0nFMjvdANq4v23Fw5i-bdpD5y0C1zi3TP0-hf55s_JXNs2nck_Kbq_jdXGeaKPwS4SGTu6mQ-6DlZ4l_kv0PWzb_pjhAIceLzC9I5ofzot58bf1a1GqNVe2ZvL1knW5e1NOXtJetryX5nYPteEDuH6zWPJ3mNbLxz2yPk57CfjeyeYeumHfwBr7ZvpDFpevD7XWFpB2PiNiANeES3yaSdTXRfimVy0SynpPBb-5nifbSdUWrvW4SK-IoTWpf45Z9ZX7BkfoFpvALlnR-APEJ1dZNggPWwad7ssUdf9n90WmWg421PxOxh7-gXG7AP81qz1MtTi9icfF5ti5-PxihqKP_It4VNUtx3u9ewMcabuBo8DlwosXF2W2wDrPhzrBu6uLEosmA8piivP7n9PZmOOdl5J4OLyzEfBnvOke8fcqduT4DDu-Rs0s41j73jLWJ_RX14qP1uyfyRoKD6Z8P5iUV9XoX6TespV7CPbkvC6wrj_7g7L48B7Rfq1MOQ-C9rPI-Y8f046LGvjEN3Vp_ieYbw9Nht6F8KWe_-If4Ywx_VfE87JLQM8Bzi7yIn9QuzyMq7jFOvh-i6U-Xcrzc0DPX7z70szcSwwqfihx8kcennzy6h9zCPJjy3uXp04ez00PK8Yu23J-FhoNwX5x0b4j8pvr7m1TcpyLuNt6XtETunmlve-DX9OnmB9PvxbkDETPkNCRUJze9gwZdRcOcZ_vtn9GQFDSI-49vrhGiDKmOzRvW1y_lq3szuqed01lBwzWyXN6fh5n_7phMOxjDMJN__GZuqu_e3MjP7wGI3Jl_qsly5l792QBhi7r8HOv5Yj5fErnmOhV5Y8ch1t4FXY9zeI64wwNzTE2wC1qDcKP11dkNKvcCuBfMzaz2J9pMarrrR-xcw2n_bma6tfm2fybebcL1Yv3lmHzFnId83AKbX03ffx12i3d3ux6CD-H_G-3jUtxtLeKIEJ-RHrxuJ-Hkf48nH-C1PzIY8Dcd7aKnn04UhtuXMHb3FuWu2sVd10zk7-5Q56l-LvgfP8jtyMM7C86ZvUU-zJardyOs0_r38JeD_iH_fgUeXZ-Ley_86_oG8DJ5tn9zrtuhu1uVPmSn133I7nRDXEqbZhe6lwJz0F4WmOcEukq-j3TFM1bCV4GvdbN2b_vnmszexHvV7_U-ZyeewbuJ-0noXgvg0Ybu73DMda6b5-J-cbyT4mVR3k_xsshhpG-zSU5f6poFrakj6sRTGwfrnIuc51OON_j04rkHdlHgdF3nsK9ehHdiCL665uLy9qXKKx_eW1V1M_Wipxzmu2v4V_TPec6fO_OqbYO3nc_pubALg9kbiqFAb9LiHgzQIe-a8TzSg8N-Wzx72zMOffDMtrFye62_XGbOnhU5ac4OP4v7i73ZNKCzUdftx9t-qrvbp1TkG25TZ79-PwDP6H14h3gWjIylfRlkuZhoL_m6w8kxirvEsT2d1fJ3w3dvvw33xhR0fAv-bUH3xTdhijiOzqlIYIo1xX4wad7ShnnWajDBr1_fduw76DveG38T7fJYxBkCvUNxN1p8Xhbxx5TGzK9i_ebkoN6d0b-Df9U_5yPm0ixtqt35OX7Z2LM30AdoCljEJxn_tCosF-JZwA_iAyZq6yMdWeyHefyUc43j2Zd5ka-X6UyS5VjktIYQfwV7A8a5K96TE_7l7udN3HTy9_blLjoz3GDsXWhvu-T9G_CvjSPGkhsx5rZxHNxP61ycgzhL4dTtI9rGqB_e7OnmtvRjg2OdIWrt3K0f3TTaRw14TfN_qRx4DaZ-iMLE322_Q7yQQrxwe9u_h3Suoa7HtNZKeUESPK2z1B98LovAofU-CZ6ciT3GPnhqjOqrSPAU--Y5TO7DeO-FIOPZU_Cm_623eYkxymZIfhVx3NdwHC4lcnaM5Jdj3FS-b6DwfVleWIuHHOzXdKPk9GZI_NTY0rI6K_f7qS7-ga2BDSd0B5WMf1J6n7j_RQonu-frT_m9yZBxjA1pP_NueqV2l-NH547-iN0Bzzwch5O7fMKVQYy9hXkFux0M9s-eey1alzB2sHFFX0SOeFTmxrriHsjxiIu5sXvG-7qyZ-fs_HH47QViwu3Tj7f9Oob55gfei0XrENHCoDE-GKXleg2-R3FSNld1IRZwMH74yJ-LczQW3pNyYkJ_Igd5mMUmmUwi5yrOXHuz5HS4rr975vZjby5C95qd3aA2GezsLqIcL4pZxP0W-D0V95AN8ufiHFY0547BwhzPQxFbbZ_yM3suzdfx7JOllTSLWAY_Z3o88F8WJ4gJNB_lmf4-biyap3hviAw3oWf_YflA3w73zyyVyKc4Q1jqj_A9_6b-bEN3BzjK8GvoD_CN-9On-DCbfvgtvVk9LJvlbpU6tlg7uUNv0n9TbyyYD4NfGt6hNxHE5drefL7RPOVcyg9hMjHXzPBxKeaf57Z6LeSW8R7PWc1BD9wT-K8cv1sx59rr-XmWKDsHle8nCVpxXawqr_1J86Lp97dZqIHvfT_gfKiBX7ZW-wh-pmNMOBP3MjXwy2WWyeUqeLI3mX4IcUxx9ewM1G_a1Dpgs_X9NvVFaVMwIXxOD6YbutOnG8zjfviz4fsh8r-7symONeHv82txhvhJcwLvc35FyS_foLHol2fW-JQWax8P8WmjsdkitL7cxads_7DT95z8F7xn6vYH_I4bLneLwJXh9q-NW9bQtbd3jlvzwb86bs0cnY2f7xm3wK5d0Fn27kRJW1_tHvYdLE6OLdNXif8R-2e5_5E8d_5l_wT2xreRNbvDP0UwhsIfxKLnQ9k-h5n0GOsvzFjF0rFUYm93xIp_zt6CienOJqn1-rm9ve302IueDHe3_g7x_z9gc8XazO_xB-eaK8ORxaoy_qj99u1g-mK-WKwJZDqTDjC3gT-o83QP4HJ8j48Of3ovi19-tL2gvb_BXAl41LI7husJj-EwcIIwsmyZ3srszvrUrgAvDeYx__wBmwpcewM4rf4vjGGni2tfknvHMOv8b9rUkVuzlWbdETv_52zqkrozS1vK7FrGn391jN8YjI8S94656X-OP5MhCy5yGcn4o_Y5_wH9WXE223BpHPuv8WdlYt78nfqTdMSI1VgacPv5HXyQ5r42YiH7iDrxYJzmDPH-zztiITE3nT5xfzeE_reRn9X5qPg_TvVDHvPNHGtRLsd3zslEDojUN98nx8kQ72BoyvFtNzTgL3jbLW4lX4t3UhZ4cd3-WODYm8S9Y8z_ZMzQKM-kybNPxkKvjH81q5R5PvfHeh3JfbH3xFTH3lUc2K_DbP0H5264l3eJ2e6ecS-fA6xDN_oz85Ml1i0ZH-M7eWR08Kgjzp2IOiePrTmazm5u_AG9Si2Rw_PQ_MQNHINJYxGJLY7vs8XDLLy-vaw_wL6uIMMTrlk3fRet5QSPrnHNB25wum8ed4__xFo-9oP-czwa4h3gd-pRxzrbfbFvjzmnjvd5y-ecKjv7g2t9wTrEOe99_Bl1rF_fxR_ca9QfjL8vyxm7OMHoAf7o4WFG--tyPtkw7j-oR1Y00a1ont6pR__qOr81s4aOHUZ_KEboM1akru0l7r3zt7F6XaTql8B_Ar5PF3f7dHF2-sk1mv7bwjP_D8Z8I205dgbsjrW3R30kyE5_VK8cbg3xDN6dehX3G-dGiSym6uaTpy9pT--3xznO7NGD49wisAysEXevPs2V-pTjhuunh93Th7sPv8M7TT1KaQ-nwR_Zu1awacoT90q0gr48ZrbXAcy9_tA-AdavuzwYE6PML_WYOJiCH3Ujaewime_85n4u-FjvQZ3zI8ee_vZ-HPAKdfcxO7Q3Q8v2pT5UMg6qY_LrQtQFpJyu8Nfh3PQRF_3ROSDI7ILnkB6Mo0BGz7cDxORKfxXM08fjhWPM-OJyp79KPh8HT-_-y_Mv0GPNa46F_JI8GnOynRstx8_SeEax561Y01gZVvDo2MLAupxEuq_Ttq_49_a7LZPB_OhBn465-9o99iX3LXn8ZD0sF4fPuTtb3RuHc7V9_af2VY8pzgWde8a7P5wLALGt7s4s_U7fo_9mDJ5QvtaDc5Sl7XHMYf_tOQqe1XrQ_zGYn7izxb2-eajmj3J9PmFfBljT1uhe55GvsRT6FGx4ZdwR80-IZJg9uSeXQpp3Vlmr0PFMrCpGacZeJU6jgdXAidkhxAHbe_mp9uF32SLqzqOx5yJa2ttIuu_x4BoLw_rPD8aeyxnlXctjT3EWS8_wvlHc_Vo7T3T1i9w9_6f3JT-T5MY-zGPL8w3PmnelMwaH-ZVpzn4Redftu_9lQXn_B5hr5HzCXMKDOaJ5Fq51bcznFOch6-JsTXavRzQVubXjI503d8y1CfEH6b1nrgMYS0gP4R2s-Zvhtbji2JKdK7j6s_wc1yI6zCjnj3IGncgFv4fnsuY_NmAP0NcZcxD9l9B296u_cfz-P2eKd6HdIkI-gx_QXfEu-Mr6uQHvZa0RblS7PMTvqZPl_b_tGPDKz88vfDjms-aaLP8egO1xQSfhefKuVnYubgHjeHG-AvlGur3jG-3r2ML658ibG8S1WF-Y4z4i1uzC84-Z3tM7dDaB8J4GpHt4TwS8h-Pe1zHdtcexNhzVvMdao19i-G8NmjDoPrYSBt53RXnPFt8YX8cO4jRAvCAO4FiDD8-HNmGQHgm6blk9Y-yL6AFe0T1IFswh8TcL6bIviFcLF7rHrkpPAPRgzu7YwrxNgRfRY6VAk6AruyuB3qPzG1YNF-Qj0I68FH2PCSbyiNP3YJvZy7oiI_hc6Olc1DafTVOhd3SHAJ7B09zibBG-S7I_Z-fW8PlJ1IXGcX6StVkH8-LePfpenC9C-J5p0Tgjzq3VcKDzKiUONMfuwIHqw9JzGjcCR4d5Kp63xTnBh_uC58eyM1LG9JzbMJ3LeLHycz3atx34-9o5xCH3DK9yZuoUe7Onxhmk59ivnt25roe-OJNYtHGu7zSGCrxPPw7mlhc-Z5fE_nVUtcOY_FBmd-CffnqF_bOfhxc6O4prosEBbH2en8XZrfNzkJdDlNexhbGTeD3kuf3AZ41qgQtdrLc9y9tmdRXqbdP74QpZ3gU3ZffDFXcn3Nv2fj5Qfp4UX1PS9hG4Cv5K2upVPrBunulVPrBP4Fb58Alcw3oArnU_XL3Kh0_hPsAHdj--GlPgIIGrqWQhg6uShUR3tAdkoT8gC03JMwkOKp5J296PL3_ANrmKD1LaHuHD_fhqKp2U2vz9PoorfZ8ELlP4Ehl_lforg6vUX12cxd-FxbjszcJTGYc-nw54fia8nd-Mpw_XcHVao49-_jpEfuTb3jk7v_-O4zL0C3Eg3StUjn3GNMpjSAfPVpafr5XPdFY4-xwVNQMiJs7viPEZY0w6b4pxFDy7vu0cca7X8KkufOWs8dXdPeVjY-Aa2x-Vz9f8HDK-5-L5djG2wjunU6XdrfI5oHPmYg6M34vxG2HQPIByTSkeqJ1rh_nbpY7b4gfVXhBnkmB-cBJntvnoBjjFCPNtX9RQgLk4K2OXF5bzOfaMsDivi78fZr7mVr57xt9FrQUP9x_w7iu8RwrjfIPkjeevOcwRavg6JsAyR_V5gRH-Ks4-4Tv7Ip7HZ0E5Z8Bnz7eKjC5eIeP12al8LmWwNvJ4DOImog_X3eBzNk_aXr_tn7XsOXewpkBxZt2H2Jnp0B_M27aIqwnzkgDnejTPEfYIv_k0b3OvW83fL8IarJJOE2LooDZvBPjerIQv5rl-4OOZb1oboPtORDuD2hV8BPjnFvwO3GAuGDriDC78xlp4ZHRW4cclL8S6RcGz6_N7uVdf4FbyKKMznwt2worYL99Y_Pr2R2BlfqM3z8hf_fCys3osuyPB3w3prNzbi7jv8Svu-eGdEOJ_Qv-DkUH_-Vw858X3JPsv2tsXQ8TlAqaYk2S2jrlR18xfXGEeP1uVn8saAppvrLlf2kTq7Asfc_HM_LMOtNVqp8ROVMCAz8k5-ww2tS79TRSe6v4k-UvALOpmDBnNv1x4r6hZcAKeFfUGwEeZTm2eoweVugCpW6kLAP4nx-lUrps863SXHvlo_cOjdQGae3z4My3HhdO9SLTOs37_hms84SJ0qNaIH67wzMQsCbP5Y_d7F_3X4br-cXgJx3Qn113vDENcV9y8LELwwR_-F1HnCuD8nOe1RPiGzkOhzN72R7Tp7SFiP2ieuctrumTjqg1WSXfBiTVsfxYO0G4PsycYh4YbZ8_-aeKF9ykxXBOo4bX4dTDZ-G02vR1mwANjk9ddEet7NB93bzgPzW3KxfUr4p84G7rasSDrK6I1nLLOyc0V51K33my98sMfCbT9Yb0uRN2Q_TPPz4gW9WQk-OCc2d_pZ5f01s3eXd_8otZRXndK1FDwor-RXqxbIc6o4nqfucaxlGqUeNFG8CPAGi1zM79XTpyPtWImztSmOEZR3JPZN-q-jzU4aI0D9FLIwaD9LtvL62PndelorovrwnNRt-Kanb-90Voevkvrb4swWyMA_L3BcpzVGCxpSh-jydKsAOYeDZqy-xvupwnv4uOjvMZvRlNW8_IBmijncuzwGk3ZWcW7aeIbjeraZncpFjSJWvD30gT2ssFcPqNO00R7jKbjgPEJ3rmYVGhK85wOQdNKL-FMSjivNThDqn3Ij7wGJ6u1LOBYJW9eC94kVd7gGXg2PoJdP9d585gOi_PgIO-sVl9eM07Urst4Q3MSASfugGPivZFNOLSPKeBoJW9GKh5TrQkWTIYV3iT5PkNLb77I9SYff_Ian2UtN6fkDY0rn8LhaBNYm1DUO1prud8q-C3ySwgmzTVb_J43-DRCXuV3lOR1EkRtQcEnLtWhc4NPY-SV1bAvuosx55Mu1en74GgVOKYUTlOnaQ9q1dDpqtw2Mp1Om3LDnK28JmpZs68iN3vUE46V18bP4Ej9T9PGFPiUcFgp86SU-aRtYxgXNGUutY0Ov9HmMcrKqPifnjShbWxkfiNW63FWz2lfp4n6FTTJfWFT_2iNPLtPq9Sbih57d8mbBZg7um3I2ytpOstssyWnNk35_QutcWL0CE1J3abmMt9zB00NH5b2lBOv-_f77FsqJ11Kk3rMUshp8vtyApqWJU2Dh2jK4t5WXJnd55fZFsXbd4yneEdtfndJgVtFh8R88TdgVcYKvTccu26rFThdY7yctrQCZzz6NK7L9EDLa8yXul3xq_Zd47MEzrzh5-fxfb4Vz5VfEvX4Nb8vtmvDacQv1m_QNZfZfte405ZXPdZIpTS1YrKMptxOru-nehw0qcWdy9dPcTPw7kaaP11rOplUdDK5g0YVnKpOJp_T6KQUA8NY2pBbZRy6J7YnOMPleBM34FRi-8mnfhLmcgAH74v2G_Yxl43RicJeDbzbms7nNWP7Uv532IanLccr-J_Vli5pGj5EE-DBsMZydv9JqTejB3V6hDW2ktpczlaNZx06bQM-9vy-ecar0k5xDqbjPCNb1_hxmBW1KG-Uh4BrWWa9rrETbf-qr3Vb2Ti1Ltasi_dff_P982--n_7e--w36We_ST_7TfpZSX--1nx29z_q-xJZ3OLP4rLWR5Tk9Rp_eS-1fYxH3h_-5vvJb-sfzZOeYu9Lbe8o_FavqcuzsUSjnCpd5AZRTfKAzs0JGor1wYfe57_3_uhx_LmV_q4NWnxRlcPjPGji8LAe3rgVVvmw6sMH_nU8MX_XHnG9zrL9Kj-Mx_X6hvvdCavJ1lPSVOQXfo7L4HHZYB6dK7fT_jCqtnqvjIcgH-O37VWGSw-_adnI220dTo_xQwanzzgihaPW35znTR4npHfbmk_qo78FnCxW-PgTsYIVPNfo64FXak2rtM176M88rulgDzow_5ZN5X67zBX-TN6Tlh4_bpeTpGVT54ftMsF8Xlbzv9afoEf7E_RUbOA36HH-BD2mih6l_27D6OG_c57QPv7Ptxf3LFn7qcwFKmsRqWpt65jVUm-ubVXmbOUaC1fOb8aiTnlzbasyn63M2SzVOsQgq-esf1eu6_8OTZs_QhM7_xGazD9D0_H3aaqvqaWWdG-gsaY2drCGfMxefmf94jhgAdZpH2nq9SvpulyTN1jXBd5bq9edpOuNTd5IaApUOqxcn1XRJNfhh2ia1PcGPl9LqdAkfMbhyi4Nn_Hw-gHul7LxBXBp7lOW_Gb3rPMEjrbE-5k79nDvWgMLVngeqrkmYvw-TV6d3-U6j_kQTcEd-xYtmjagA5thQ4fkNKnzRUCHnEE9D2Fep0mui3fAsVR7b7yDNzre_bFs-tPSvjjrC6fP2qmcx9rjPPZSi28bPH40Jwdo4nNe9cu431IZayr765sumsziPmpBk6Gyb_YQTV6dpvtsoUVTw5_eawtNmnTV3gTrsm8pTRWf1ZMmNq7RlFZ0WOuAg3fh1PJOWF2HeWU_QlfvI7ThNPbtKjy2OniM93s4TVt4MJdrjjkwCavlr6xq_qY3b-r7P5XcDucRmu7Z02jl3OHYUl__b-RknO_xWahrl6Sac0d6I807WHXt98RLu7anodV9VsUWFPt0cpo2qthCf4Qm0r3zg7rXpkmv697mHvuW0IT4rWQ0ddi3xy1u5XedlnKS2lOXnDBWH6UNmipyqu7LdfjzNhyzHgc4lZjUewQOV8YTqZrHOHewsjtvy7ivwmN5ntMdcJzG_vfmnnFKAsdSybzLNnF-FluVvVjGVePUQzw2VDzuoGmAd9Eua_mRq3r-zF0-9Aj-zzJq_qaeu5Xc4UM51fYJjlpDj3UpTUp5Wwne9VSfyziq-NFQ0sQnJtXvfKnLSR5bK-WEecswJ6rNzwzVWKeWk8XxDqF6LvZKRVNFTvMGTZinOTKX2fzMMZ7MbH5G311jq7VyFqp2Ip1n1XOpQC-R3tS6Zn3k-aDi_MH5YK7z9dzchycVPUmze-vbfvyLJCfTbuZOV_fb59WYRr1GYY_wbMGgao-N_A95jmhzr3x8MRm_mHV8JvL4XuHzaK-cQ9zY9DGV_X9xV-1n6zeUvzhgQXM-L50nqOfzeQ5ZI2-katcKHxw38RE5bY28-2oetn3PupQMTkNH7xgTRO7zCPz5ojGP2sjmHF05YzA-eobVzKuprm-dP7eZLC_cbK39lXweSG2ilVOJeeHzRv7hpDof41JdbsMBui7Dhi4P5HCUa5EyOM0czzvzX_Ee4Usz7-izNZd2rijey2Q3dfCz2LqVL4Z3Fw9Z019UZH4vj5eYW7PNzmW9rIuza3iuC2u2tH3xZ_Yr8cV43qq5blrNHxvfY7-gVzaewVpnOf7btILrxX_BWj6WSf7efL5S7SCOex96QPsGZ4wHLvTd2VPNoCHljGB9GdMR4yfeaxs9nXGvBJ_5RhJjjSlxZvjJdKKnofWlRhfViSievXY8O7efuTt_6L2s4kweYH8blC2OH6FrhrE_ntRpJ_zF-VYpvOy8qgzH4pkEx-KZBObbbPvu7AYyHH-4u_B0CGQ4nsK33fRDCi87Fy7DsXhW6rmGc1q6Vzx_ds6fWWBHEGNRraiQaqTJYBbPJHQXzyR4etH2w9ldZHSLc7y2hO7rIjxE03cpPHFeVwYP7GZ7OhTwaNw0l2Ph09zd9GdJ8zyhvQwTz1T6N2_mVvcGUyfaXsQ57WF9T_fqNfaZyZbRR9zonG24eHeME8RJA1HPPsr2da_FufL0bRdna-i094xxMtp8gvSI-yQXgcjpwc90b3XF_841POvuXZnA7-qLO6Fp7iJyrA_iXP0HzafFGUtx_vuFaveknvHE0T-AD_nh1PYMt-UZ2Py8eFqpz_YlO4Nb7DlOz7V7xq-LMsf3Ku7MBJnqzMZ58QrHifQb8k_w4688DvSu78gb0odvUXFuFu_hzu8ox8_EH_LL1-eLkCP1-dchPzMLn128V53m1gvuRtXz6uuI7kwvZQc-Gu9Ap_1V8Jse5v1g_IM6Bv8xT9PBGlQD6BOebwZYA4qN13Su_7Cnekq5vmhu5NZqL3gvgCOty2XjdYY_1h1mdE_fNnaigj7dmy0uVh4XG_65QnvgGovYJf8l6rLV8xRY4_vp6hh0v3nlbnam5TkK_k6n88cU7xrJle5nz2vJ7bX_7_8Ht7dTvmRoAQA,",
              "s": "eyJzZHMiOnsiZCI6NjEyLCJnIjozNDIsImMiOjMzMTkyMDcyOCwiZXMiOjQ1LCJjcyI6NzUsInBzIjo3NSwia3MiOjMwLCJmcyI6MzYsImRzIjoxLCJsIjoiY293Ym95MjUiLCJ1cHMiOjE5NiwidWFzIjoxNzAsInBsIjo2ODR9fQ,,",
              "sk": "{{sk}}",
              "ui": "{{ui}}",
              "w": "2199"
            }
        """.trimIndent()
        )

        onFailure { false }
    }

    packet {
        i = "V900"

        parse(
            """
            {
              "pi": "{{pi}}",
              "pl": [
                {
                  "i": 1001,
                  "q": 1
                },
                {
                  "i": 1002,
                  "q": 1
                },
                {
                  "i": 1003,
                  "q": 1
                },
                {
                  "i": 1004,
                  "q": 1
                }
              ],
              "sk": "{{sk}}",
              "ui": "{{ui}}"
            }

        """.trimIndent()
        )

        onFailure { false }
    }

    V303(10868)

    packet {
        i = "V876"

        parse(
            """
            {
              "code": "$code",
              "pi": "{{pi}}",
              "sk": "{{sk}}",
              "star": "80",
              "ui": "{{ui}}"
            }

        """.trimIndent()
        )

        onFailure { false }
        onSuccess { true }
    }
}

fun 邀请抽奖(bai: Int) = buildStrategy {
    version = 1
    description = "邀请抽奖"

    V303(10868)

    (1..4).forEach {
        packet {
            i = "V877"

            parse(
                """
            {
              "index": "$it",
              "pi": "{{pi}}",
              "sk": "{{sk}}",
              "ui": "{{ui}}"
            }
        """.trimIndent()
            )
        }
    }

    (6..9).forEach {
        packet {
            i = "V877"

            parse(
                """
            {
              "index": "$it",
              "pi": "{{pi}}",
              "sk": "{{sk}}",
              "ui": "{{ui}}"
            }
        """.trimIndent()
            )
        }
    }

    packet {
        i = "V878"

        parse(
            """
            {
              "type": "0",
              "pi": "{{pi}}",
              "sk": "{{sk}}",
              "ui": "{{ui}}"
            }
        """.trimIndent()
        )

        repeat = 21
    }

    packet {
        i = "V878"

        parse(
            """
            {
              "bai": "$bai",
              "gi": "0",
              "pi": "{{pi}}",
              "sk": "{{sk}}",
              "type": "1",
              "ui": "{{ui}}"
            }
        """.trimIndent()
        )
    }
}
