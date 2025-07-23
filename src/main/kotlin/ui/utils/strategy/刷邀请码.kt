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
              "m": "c9516215dd37a0c3800acd1953a2ab33",
              "pi": "{{pi}}",
              "pr": "H4sIAAAAAAAA_61cSXfiurb-L3f8Bm6g7q1hCGDMK4uHAxhpFuycGHdhXZK4-fXv23IfTCjqnAErbiRt7b6T86-XfFmIR_MH_uZubv6wNm5qFWax2swydkyPh6P5w4wydTWdR3yXqcwQkUV_Cz5ic_zdhDlT6S8v6H41XR6tJ_NsxtZxdVQyqwhHLAjz1cZSfz0u9Wcdz4NZyoL1mNYXsQjM4O1o5SPlV7A-YszZc-Yp7ck80jo2vc9Znh69_fLDDE4H65hqv6az9NfUTPdP2GO8092Y0bjMmnJlteGYN1bcZFnQeBrjLeycS_weNDYNNcBR3Li_Fy8uxziqcjYTEQqN1jwdQJsQ64dy_WCN8Q855tOzMz0DLcZs6uZ_7Wge1tAfjqvo3cOYd2-x7O8rsb-u8_51HwdnTfc5mz7QPo98bx5XsfAPCxbhHn_p_QNw9FMJq32X0D45eCbi-dnVthjj5VyzzzUdnhfL1O2-T9hZwm_XCF19cubxmnD4txkv1UNsHz0jBMytYk1nck-eMw4PmnyWAze9fsYTufdzNS_yHqVMZavNq2oF29TaK2fQsxprn0ALFXimFvbGk92HMH7KZ1bQfbZUiGY1DLGXMHRrA9namKo1nf_8tTg3Y5-fzB9ss06tqVnv47N8ZuLZ67nBifaW1zB2SsnL2Xg13eZs85D99ZQN4P9QXOK_zf8u_uzxAv8cct7Fv_gOf-Ktu99FnHhZ8i10HXY-AL6TLyOhR6k3dY80jsd2xjXSz-XnIRSnQxwVz4-SVqFnRGGzJ10lGc48Z3cUC6uW6QTvNbbZFlJ_NZdoADk6QdfeUryHvLldeYp5AhmFXngOyaM4uobZuR_rLslzR76FcfGM1s5WU9AkWAPWRHG_2BHCE89PbjxXxGaWmqTPmlLvWT84u7DSZb2Spai6J3qTTL_R_kuehxUPX_F3ImWL5If48Uz8XEgeniqefJm3buZJnjS6tpPreHm5jsB6jl6t-3SxbjYAKx-ANbBv63Lfm-3FM_D38ll-AXNg_cu1rOLy2erxEid2-Ww8QPf0O5o2MIPL_VuBebmPaT13fp0feZ8fQ7QZ5NHlswEeceV3eDRAG3VgrQHeDqx1ycfR5VrhwFpD6z9clWd5T_almJ0hr8WvKT9bG1wH7pnmwT6daU0rmJAOakQfopmr9XXXJfvQ1WXjZ-HBJkE_Y-5khZBzxqrnSNt0hF3_ATv89UexQyKOtJ8H2o9O-4EHP1vF-kx0AmyKaZRf8AHguWI9piSTGntKC0v6fDutbFjkLSZ-GztMPprnxjjlTxLnMcUf-GX45fgBfxNrmyp-iE9MwDdH-GGchXFb3FsYa2GshbEWxiImmloYa9E7jN1inIXxHO_XuF5j_BrjXbqnH-asMQd6P11jzhpz1hi_xTj4oOkWY7YYs8WYLcZsaU1aj36jMlbawiaeMxkL6G_yGnuU9MDYM-iDORI3PHfPK9CM6IT3GcslvTLQK4N-6_jleJeTjlnBK-E6wrgcz99B6xxrFTSG1lhBFpgc95CWuBBNZoARFh36qSV9iI6vNA7-WsYBivxL-ww8ac9d7VzbdhnPkO7KWDWZfHraLvf2E4U7THl2mH8wmPqsbUk-Tq0OjMj_qa6B-Mixo30h_nK17NODn2vGlfHcmPSA_MbBSWnOx0EbR-Rf4JvILxOux0NSXcO_I2bNaY6MI-N5Xs33pZyXe8b1JK3li--Bw6J89xfJm6N0_GP2_nIk-zMqaSFtU1j6f9AK8XUm9TaZSDjgB-LYURkfJpNAxh-PFFOKsKtP2HtQyfTZQ0wvxwUzhRXrkVVwRdqQhR0Rba3CorVKOpc4FVLvpc4sA1e3gxoXxJ-FZ6Qtb7TMr_0s1yenyveeuJZKmyztjMPSxr86ZUxd8igKXKO2l1FAPr6Kn5q1yHYPz-HX5zxdm2PqV_e2eRi175Zf312FtboKS8bRw7CC7noyBgv43o5l_kJxIl1XdJU6vZ8kh5ofsZoKbdfE-y-I73nt26r1WSlHHT6w0pYbc120PEZcKD6xzr_B40kZx5rQQ54Sn172lFO0caBnsPHBILseQn4eyOcpyJ_OtOdfm8q2Uw5n2Kcv48Zib5_W2k_1kNhrsV_O-D5y8Ld4dn5-9HOnCPY3mh703Yc3n0RuaPsvhE-wVSGztOeSZhsTNumVZFt5cXYyH-jkO6lnZGVOFpij1RS-DjrgLiaBW-4V67z_IFuCdbSVlGlPq2NbT3uvr9887T9kP3OinTAypdang1HnZ9n5pdJJ2Iecclrkg6oo7QXszrqj5_Mz_ExYraG0a_iJWHTj5exMNqpev9HvxbJon_sh4dLmgHbhJk38_ib0pZQPzIONha_8-ndO_M9SN1ZD9lTn4Qz88IgfRx6Ympg-IOfdjpkmAq4tQzHdHZHjjHg8S0UwG5OtYoa0pcj527nwxdpqKnzL2UXWNMwFrlcGz1bGLpDrbbYq18zc2shaQEJ4mMG6QA4b8MCLePBQ8M3SZw7y43imY67KglBfTWcj3Gdkl12dnYRG9t1UYOUyprEjcrBiNXWxN3O8cizkV6-QkTAFHpDprc4o_0nIXkWJ9CPB5CimbsaCV41vYBeRi6ycWcY2uyOLLVXKvbPVKEevfEEDU0DWkYflK4ch17IUK15jDZ6vNq7KAz_kwSveCx-6QbJGuc2RFdsCdlaFLRlTTQQynYp4ViBfxT7nIZN05cBR0iWV_DyaKdt4gQi2Gad9bViIXNBfbdYqaA-6mIWYzkFnVxUSv0k9b8ycZYwf4LCQBRbySPgSh4NvO19sRIQcmNaOxFHOKw4LOU8VQRTwwo845Z0a_LPDsI4dgVYF8ByvjHUKnfJXVANKdqnkX0zyMcEv8i3DGlkai1eIj4SzLlgwB19d8H0NHJZHmbc5XlzOY8HK2GbCAG8Dz7diEYKHBZtOIsqp2WZ-FNg3cvewzPd2kpbCsEM2DVXw3Se6M2g2c7hG9BGbULNiPC9mCi9CoqXK9zIXjsmOCNg3Np3HYjMHHezYmoKmDod9CXVevII-a13SJJ5XuEEGnCXkkmoDXgg4qthApgI_EoC52swD5F86R65Ge-R7Vs2zQ_IpVmAHhBNkUGGapQhHxCvI92oTwcZDboutLuc5UVHqzy7GujnoBT1ysS_EXI4dUC2J5B96kYJ_kANXygnfe3IerSFg4xobpdmnpsaW7HD_Xsa1aqn7fE_-S_ptee2WdYFM1k8S4btkw6Nzui_901lQ_OGEVe3BeyeYle2lGhT5iuMm_pkfnPlZ6ksdrxTrlBUctpiTP4rdhYy9qM4E-Qt1NrVKvSzzHxX0H5M_YIn0s8mB7JP0C5KX0g-aoe-7pZ58VOsh_oTMg3-srEPWcHTETuDRLLWQw7RwTA05DH5m5bOv4GPsEuGMwy4-bOqOQPucUW7QwimkjSnwfGN24Kyh77MMep9WuVqJT-xh_54Pf6hS7a_Fa_wJm-wfZK1mXK0xS4GbskIOAZmg-kr9HHL_UFjBGrTddmC6Y4wtWBFqbP87MC9oOQJuwIWT_ezA49jHGvBmZY5X8zd40GFrwU-z-D0cswjxy4fo4wh7O4P9NvHbdmDK2Bd2zlV6MDdrimNhW3ja5R_shPaMGP57epop8R08USgnF82aD1TnHUm57MGy4CMeyG6rPZmk2l8cSVkXmswbTMT5PVmhGh94lxGN-rBeM-RU-mqz1Si-bWG5VGdHzD9TLf1bWJZwvOjwRQewT8By4RO3XV0bk1yu4MfKGKvRAegl_Mj0FTriyfhUUD3tuITdRqxivN6SyxFDfMc2r7DXZodnhJcJ2ZxlXTrSPdEWMtTTOdh0_7Cw376lo4xLwS_Ko1u8IBuk8zON7AvFbs3z6QP83ZpihvQGrCE6Qv5JpmGvg9euvsEfbcdkZ1bTro7PgH8I_Q9Bz3lLR1nT-sYuUsxB9WL4LqsnG_BPBcXTsPvHrmwgVtjwjPzeqqNnUq6uyt92DPtbUB25QzfYKPhjyDTl7B08NOCgSf9b-MN4DNjD1RQxS0F-ajbu8oZskKyL92i1hbzR-PWILUq_4hpjv8mlk8lH7Zdk3rOY-KL2OckkondCxv8kSybBSyGr_nPZT8lkXlD5K-RPzTqg4anKc8rc7CjtUdnHKa9T0T7_fJZ7oDqCnFv55UnIYRM54gvEfzr8NmKJ19SK7SNzEDPEiHoK0mupN20u52T_FV_6NW2e7X3IukCnDi4WXq8uJoyoGKp982Q56ucA87DsMz3IOr9rsBZ_AzlRk89PAq_tXwXc2SUYp7jJLpK1goX3wR01IpwRE-TIRWUtxNXGn736gpNFLjBueOVkybMj636yRtrvQe3S57I3V_Z8NKFVdYL3g-Ehl2aKrLmUsUv6rG-7NDmL2Ktzr1gYnt_bhyESz6B6COUIJn6IWR-r55RXDT03ZhfPRWwn3Bn1-hoEq4dHTD3DeQ8-3yNq741Rf3h5k6vLnFP2oLQsaXuPE-RrZtNvoTiwwwPqO3bvTweNeozNvSYMkTZ5fCIU6EnhGT8_xNPAM2epvlS9EZnHauo71XZa3szfDote_zEQVN-RcmTnVe84cKs81F2wOoeF7tU5bNTvOca-nNe978OcvLl5nde2ugYaRVJPY6aIKq_miR3V7w9Gljf5MK7rNdzYb2olrpSV-nr-UY8HTesab5mXy_rZsqmfQc-Tur6GXFup16ZcutEd6K6AX-z1Y5NJr-7mJsvPek3kxInQrRoWbKCd9GQHuWjV56p6ffA701kpN0lNW1zry1r-37mmqu27qHA1T-bQpFcCOXwP3rHCzZgXHRsbP1f1ItcQZS-ScmRpJ8TRk3KlSvvfe__47fti9f18qhN_-55qR9--P954fwM-uwHfugHfugH_Bv2KG_TLb9Avv0G__Ab98hv0y2_QL79Bv_wG_fIb9Mtv0C-_Qb_sBv2yG_TLbtAvu0G_9Nb6N_BPb9A3u_E-vbG_7Ab90hv7y27IR3qLfjf4m96Qn-zG-_TW_m7IR_p1f2X8ucu9Y23Lx2o_7vDUujfkaWrT6_GSicK1de3bNXdh527c3OfIPz7qeg31rw5lDJrTuZutTjWWcWLHO_juSONlLqvxeF7lNcjVaJ5u67KGKWs79qm7vtDt5pyGkH0beQ3fkMkzFGX9G_FpLGRM_7_zSY74wufIL5_39nir7ZT_M6IPbzo6yT3GArlJ76wG5Spn0Ys9EV928KI-RHUuKpU9CH2iNH4pHgegSeX36J757TvqmzXnW3yuNX6PaCX37xTUk51pZQ9zRnqX429R1alC5EBNTCqMeRsvxLbSxN0J8_mxOX-We-Bp2wuOECv0zrd8uDrrxshv7mLy-SW-Sb-cZ1Fe9l7Qj8-XP9yENzX6Z-On6jbx4O7sLkTaiyNw39T7Ezs47JvaXoBYu-mnIR4413EL_LzsC5BvF8a4ypWo_k08t4FHfXbJ_vAMRZ7fK_Nj-6PKoVKq_RAdq35nVl7blD-rA2NHd4wd__5YK-2MVTtji4Gx2ZWxQ-vmV8fKXvAEuWTDo_TZmYdtDjX_bHu-rByXnEH_-anXfyJ5jlvZluewajnvXMuYu34eh518rM3bsHZSwlR_yrhci-IqPk5kXtyN2bXdZy0HXPOi58f2WtYHSliYZ-e1HGHOG-_MOXTmcI1ypIeP-r6RVbnGz9LeyV64L-vKnf5bJHPpjr14XizzxlbG0B-Z-5w7vagor_NlL1kWrZ5OiprOyDtOTdwrn2fI4zr3e_bZ8Apjefyfpt_OqV90rPIYLfrgfX5RH7KnezRenv2DfhCdMKexI_SOl32gY_UuLXuzFY-aHh0Ledy9bngQeo3t9oKOvL1hrTq3Ug9x9sm1d5knOYU8X1M_Q_7SygtyHK2O85G7n8Vje93Sy9bFgmm1fT7s7VzuWfaMaYxZVOceA9l7fWzP0VY9bdpXbhV1_k693HV7pqesi5b2JrCqc5ns03PGStvfFVUPvIZnab8BT-YFt-CtNm4PHgusMRuCif13Ya42r9iH3RljVedHO2ttqAfo98fI8wA38Nuse7DYhvpa9tcx2W_QYGz1aaBZxatSn_2qaKCU50a-3xMrzwRcwtvJ3PZ8IL1NqO6XKV_OUieHr-ecMb7WgzI3H_tSH0u5PNM9Pza5-kejq7H6eUjEyS3P1sheldAFbIrauz9oWdjq3NKvZZxrdlFfH7762GRy5K2NANzKxibs5LXnW0EPiqe6Z2VOzZqCxjb5PvLmfYPTh2s0e_4U8bLxDQfNlvGQ3JNW1kzK2p88F1LHemeqgZUxxzyQtJb08I5tPdBPPa13Vvud6506QCzaOoC-LBr67Jt6Xuo5DZ7vh2SS1PFA5efK888bS6OYl-KU5_0r1Y53h5i9IXZSqKZTyt9YOTzVZ5vDgtF5bbIrRjQinHa73dKeLU-Iq7bPgP87czbxThf75dpFfCtITks9LmORsgYytgpenoFCXPtCdc9oCV9EPSovWsuz0ln0W_NC9unGYxV6Az8--739OT-xpn9-2TLZEyv1Qk07_vmdfEt7Lsc-y3PjMl6V-gp7Ymayf0_-R8ZaXqmj8tyC7ZvlGVLwcluf8VLonAwrlmXO4dhKG2eoabW-hP28EMcv8LLqnM8lvKdv4AXu2ApYxzbMSpudIBeoa0mlL6K1i7JeKm16Lp7qb0jWOSvCvOpT1bFmmcctlp_V3KRan3RQ4XXMqtPZbJLFB-odUb-o6Ng84LT9B3CibwFmQzjld-EUVLnxBU7mPTjllX-9Ty4GcVr_fZwqP36Jk3UPThQz5IM45ffgRD7XGsKpuBcnWSP5-zip_wxO_J_BaVCf7sZp9Kc4lTZpXIiFXZ8XaXV9UIau6zriFNjp17Sv61dk6Lqu05nYQp7r6-mFe6de0Ddw1qgXR_0BvQdwkvXyIZzYXTi512RIvwunP7DJ9O0a9pN_4dMgTt_xiVHdf7P84me2d_oZ2fsfW0kfJzZok-_GSf_7OP2JnxnA6Q_8zABOf-JnruB0r58ZxokN2oi7cdL-Pk7WNT59Y5MHcCqu8ek7G3GBU3GVT9dt8hWcBvl0N07DfLobp2E-3YXT-hqflHtwYtNrfFrfg5NyjU8y1rgLp0E-3Y3TMJ_uxmmQT_fjNOhz78PpahyxvQcn9Voc8Z3PvcRpey0WVe_FaZhPd-M0zKe7cRrk0304Bdf4xO_BSbvKp7tsOb_GJ-1enIZzhrtxGswZ7sdpMN67H6fBeO8unPRrdQV2ly13r9UVvothL3EqrsWw7r04Dcfld-M0GMPejdMwn34Dp7IuFRx0r18rSk5-9_7ZyILu_SHu3Sf0XVZ7fm8edmtNPP7ZXfvDXbRjPWepuFozVuW6fPdvWQd2WE793i7c6n0Fx4vdpHtv_7cLV-zN6ryn3f1_DSkLXNKDundVyP53LE4uaOXGu-DZmOdC2zY1Ox7vfrQwWOgteFtHNeT3XsqqfPfmxfLb5VH5HSXzZZ-2Ggs61N-DvXNt_MnLb5mplxmADlpzTk3fKdTXedEl_LPQbOVQ14SdLEH-XNdufa-iF_YfHOq6M-Slep_K71Wqfl3nXGQgqO9fnyvVwPvFMqrhi0Uzv-njicWyrWsvINd1n0hT_fKMn_w2zvc0Vu1nJ7_no_zRTLxzWaOXz5OX_USxHrvy7Jb_H8Lxxu5inXXqDyOLvn-m_ukeaxvbtH2HeDVYK_IbWC1LRff_RiQ7_8uZ0ZYPyU7nzrKRMTcG3uW5xI_eucKY_vdDp48X79rvd42mZ9d8R9ucl02Wp-b7ymQZtNflWYryHDCde6QzxF5JZxl7l_Xjqpf7IXODsq5-bOgY27mr_az_B89b_6zoTml4XvM17_z_AuJN0vk2MKnidXlekXWuT3q1Tv4Sz2qcftRr4n3bx04modWeeQytp871sRnfnptMTnG7DotFr9drx_IMaPesxILJ3nBVH6Jz2nQ-Iy3_V4C045n0l_Qs8GXP4rC34y7fPEPIM4r1t5TuorPnRbtnb0_f8D3UMgKdl3JY9baXafMOesS7uBryrEV9niPoyWHsJ1yjbz07ZzEMpjTfXjuq3vBKy5KD0Z5V_muv_M-__h-xSQJLckkAAA,,",
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
