package io.github.smfdrummer.medal_app_desktop.ui.utils.strategy

import io.github.smfdrummer.utils.strategy.buildStrategy

fun 账号激活() = buildStrategy {
    version = 1
    description = "账号初始化激活"

    packet {
        i = "V206"

        parse("""
            {
              "m": "c9516215dd37a0c3800acd1953a2ab33",
              "pi": "{{pi}}",
              "pr": "H4sIAAAAAAAA_61cSXfiurb-L3f8Bm6g7q1hCGDMK4uHAxhpFuycGHdhXZK4-fXv23IfTCjqnAErbiRt7b6T86-XfFmIR_MH_uZubv6wNm5qFWax2swydkyPh6P5w4wydTWdR3yXqcwQkUV_Cz5ic_zdhDlT6S8v6H41XR6tJ_NsxtZxdVQyqwhHLAjz1cZSfz0u9Wcdz4NZyoL1mNYXsQjM4O1o5SPlV7A-YszZc-Yp7ck80jo2vc9Znh69_fLDDE4H65hqv6az9NfUTPdP2GO8092Y0bjMmnJlteGYN1bcZFnQeBrjLeycS_weNDYNNcBR3Li_Fy8uxziqcjYTEQqN1jwdQJsQ64dy_WCN8Q855tOzMz0DLcZs6uZ_7Wge1tAfjqvo3cOYd2-x7O8rsb-u8_51HwdnTfc5mz7QPo98bx5XsfAPCxbhHn_p_QNw9FMJq32X0D45eCbi-dnVthjj5VyzzzUdnhfL1O2-T9hZwm_XCF19cubxmnD4txkv1UNsHz0jBMytYk1nck-eMw4PmnyWAze9fsYTufdzNS_yHqVMZavNq2oF29TaK2fQsxprn0ALFXimFvbGk92HMH7KZ1bQfbZUiGY1DLGXMHRrA9namKo1nf_8tTg3Y5-fzB9ss06tqVnv47N8ZuLZ67nBifaW1zB2SsnL2Xg13eZs85D99ZQN4P9QXOK_zf8u_uzxAv8cct7Fv_gOf-Ktu99FnHhZ8i10HXY-AL6TLyOhR6k3dY80jsd2xjXSz-XnIRSnQxwVz4-SVqFnRGGzJ10lGc48Z3cUC6uW6QTvNbbZFlJ_NZdoADk6QdfeUryHvLldeYp5AhmFXngOyaM4uobZuR_rLslzR76FcfGM1s5WU9AkWAPWRHG_2BHCE89PbjxXxGaWmqTPmlLvWT84u7DSZb2Spai6J3qTTL_R_kuehxUPX_F3ImWL5If48Uz8XEgeniqefJm3buZJnjS6tpPreHm5jsB6jl6t-3SxbjYAKx-ANbBv63Lfm-3FM_D38ll-AXNg_cu1rOLy2erxEid2-Ww8QPf0O5o2MIPL_VuBebmPaT13fp0feZ8fQ7QZ5NHlswEeceV3eDRAG3VgrQHeDqx1ycfR5VrhwFpD6z9clWd5T_almJ0hr8WvKT9bG1wH7pnmwT6daU0rmJAOakQfopmr9XXXJfvQ1WXjZ-HBJkE_Y-5khZBzxqrnSNt0hF3_ATv89UexQyKOtJ8H2o9O-4EHP1vF-kx0AmyKaZRf8AHguWI9piSTGntKC0v6fDutbFjkLSZ-GztMPprnxjjlTxLnMcUf-GX45fgBfxNrmyp-iE9MwDdH-GGchXFb3FsYa2GshbEWxiImmloYa9E7jN1inIXxHO_XuF5j_BrjXbqnH-asMQd6P11jzhpz1hi_xTj4oOkWY7YYs8WYLcZsaU1aj36jMlbawiaeMxkL6G_yGnuU9MDYM-iDORI3PHfPK9CM6IT3GcslvTLQK4N-6_jleJeTjlnBK-E6wrgcz99B6xxrFTSG1lhBFpgc95CWuBBNZoARFh36qSV9iI6vNA7-WsYBivxL-ww8ac9d7VzbdhnPkO7KWDWZfHraLvf2E4U7THl2mH8wmPqsbUk-Tq0OjMj_qa6B-Mixo30h_nK17NODn2vGlfHcmPSA_MbBSWnOx0EbR-Rf4JvILxOux0NSXcO_I2bNaY6MI-N5Xs33pZyXe8b1JK3li--Bw6J89xfJm6N0_GP2_nIk-zMqaSFtU1j6f9AK8XUm9TaZSDjgB-LYURkfJpNAxh-PFFOKsKtP2HtQyfTZQ0wvxwUzhRXrkVVwRdqQhR0Rba3CorVKOpc4FVLvpc4sA1e3gxoXxJ-FZ6Qtb7TMr_0s1yenyveeuJZKmyztjMPSxr86ZUxd8igKXKO2l1FAPr6Kn5q1yHYPz-HX5zxdm2PqV_e2eRi175Zf312FtboKS8bRw7CC7noyBgv43o5l_kJxIl1XdJU6vZ8kh5ofsZoKbdfE-y-I73nt26r1WSlHHT6w0pYbc120PEZcKD6xzr_B40kZx5rQQ54Sn172lFO0caBnsPHBILseQn4eyOcpyJ_OtOdfm8q2Uw5n2Kcv48Zib5_W2k_1kNhrsV_O-D5y8Ld4dn5-9HOnCPY3mh703Yc3n0RuaPsvhE-wVSGztOeSZhsTNumVZFt5cXYyH-jkO6lnZGVOFpij1RS-DjrgLiaBW-4V67z_IFuCdbSVlGlPq2NbT3uvr9887T9kP3OinTAypdang1HnZ9n5pdJJ2Iecclrkg6oo7QXszrqj5_Mz_ExYraG0a_iJWHTj5exMNqpev9HvxbJon_sh4dLmgHbhJk38_ib0pZQPzIONha_8-ndO_M9SN1ZD9lTn4Qz88IgfRx6Ympg-IOfdjpkmAq4tQzHdHZHjjHg8S0UwG5OtYoa0pcj527nwxdpqKnzL2UXWNMwFrlcGz1bGLpDrbbYq18zc2shaQEJ4mMG6QA4b8MCLePBQ8M3SZw7y43imY67KglBfTWcj3Gdkl12dnYRG9t1UYOUyprEjcrBiNXWxN3O8cizkV6-QkTAFHpDprc4o_0nIXkWJ9CPB5CimbsaCV41vYBeRi6ycWcY2uyOLLVXKvbPVKEevfEEDU0DWkYflK4ch17IUK15jDZ6vNq7KAz_kwSveCx-6QbJGuc2RFdsCdlaFLRlTTQQynYp4ViBfxT7nIZN05cBR0iWV_DyaKdt4gQi2Gad9bViIXNBfbdYqaA-6mIWYzkFnVxUSv0k9b8ycZYwf4LCQBRbySPgSh4NvO19sRIQcmNaOxFHOKw4LOU8VQRTwwo845Z0a_LPDsI4dgVYF8ByvjHUKnfJXVANKdqnkX0zyMcEv8i3DGlkai1eIj4SzLlgwB19d8H0NHJZHmbc5XlzOY8HK2GbCAG8Dz7diEYKHBZtOIsqp2WZ-FNg3cvewzPd2kpbCsEM2DVXw3Se6M2g2c7hG9BGbULNiPC9mCi9CoqXK9zIXjsmOCNg3Np3HYjMHHezYmoKmDod9CXVevII-a13SJJ5XuEEGnCXkkmoDXgg4qthApgI_EoC52swD5F86R65Ge-R7Vs2zQ_IpVmAHhBNkUGGapQhHxCvI92oTwcZDboutLuc5UVHqzy7GujnoBT1ysS_EXI4dUC2J5B96kYJ_kANXygnfe3IerSFg4xobpdmnpsaW7HD_Xsa1aqn7fE_-S_ptee2WdYFM1k8S4btkw6Nzui_901lQ_OGEVe3BeyeYle2lGhT5iuMm_pkfnPlZ6ksdrxTrlBUctpiTP4rdhYy9qM4E-Qt1NrVKvSzzHxX0H5M_YIn0s8mB7JP0C5KX0g-aoe-7pZ58VOsh_oTMg3-srEPWcHTETuDRLLWQw7RwTA05DH5m5bOv4GPsEuGMwy4-bOqOQPucUW7QwimkjSnwfGN24Kyh77MMep9WuVqJT-xh_54Pf6hS7a_Fa_wJm-wfZK1mXK0xS4GbskIOAZmg-kr9HHL_UFjBGrTddmC6Y4wtWBFqbP87MC9oOQJuwIWT_ezA49jHGvBmZY5X8zd40GFrwU-z-D0cswjxy4fo4wh7O4P9NvHbdmDK2Bd2zlV6MDdrimNhW3ja5R_shPaMGP57epop8R08USgnF82aD1TnHUm57MGy4CMeyG6rPZmk2l8cSVkXmswbTMT5PVmhGh94lxGN-rBeM-RU-mqz1Si-bWG5VGdHzD9TLf1bWJZwvOjwRQewT8By4RO3XV0bk1yu4MfKGKvRAegl_Mj0FTriyfhUUD3tuITdRqxivN6SyxFDfMc2r7DXZodnhJcJ2ZxlXTrSPdEWMtTTOdh0_7Cw376lo4xLwS_Ko1u8IBuk8zON7AvFbs3z6QP83ZpihvQGrCE6Qv5JpmGvg9euvsEfbcdkZ1bTro7PgH8I_Q9Bz3lLR1nT-sYuUsxB9WL4LqsnG_BPBcXTsPvHrmwgVtjwjPzeqqNnUq6uyt92DPtbUB25QzfYKPhjyDTl7B08NOCgSf9b-MN4DNjD1RQxS0F-ajbu8oZskKyL92i1hbzR-PWILUq_4hpjv8mlk8lH7Zdk3rOY-KL2OckkondCxv8kSybBSyGr_nPZT8lkXlD5K-RPzTqg4anKc8rc7CjtUdnHKa9T0T7_fJZ7oDqCnFv55UnIYRM54gvEfzr8NmKJ19SK7SNzEDPEiHoK0mupN20u52T_FV_6NW2e7X3IukCnDi4WXq8uJoyoGKp982Q56ucA87DsMz3IOr9rsBZ_AzlRk89PAq_tXwXc2SUYp7jJLpK1goX3wR01IpwRE-TIRWUtxNXGn736gpNFLjBueOVkybMj636yRtrvQe3S57I3V_Z8NKFVdYL3g-Ehl2aKrLmUsUv6rG-7NDmL2Ktzr1gYnt_bhyESz6B6COUIJn6IWR-r55RXDT03ZhfPRWwn3Bn1-hoEq4dHTD3DeQ8-3yNq741Rf3h5k6vLnFP2oLQsaXuPE-RrZtNvoTiwwwPqO3bvTweNeozNvSYMkTZ5fCIU6EnhGT8_xNPAM2epvlS9EZnHauo71XZa3szfDote_zEQVN-RcmTnVe84cKs81F2wOoeF7tU5bNTvOca-nNe978OcvLl5nde2ugYaRVJPY6aIKq_miR3V7w9Gljf5MK7rNdzYb2olrpSV-nr-UY8HTesab5mXy_rZsqmfQc-Tur6GXFup16ZcutEd6K6AX-z1Y5NJr-7mJsvPek3kxInQrRoWbKCd9GQHuWjV56p6ffA701kpN0lNW1zry1r-37mmqu27qHA1T-bQpFcCOXwP3rHCzZgXHRsbP1f1ItcQZS-ScmRpJ8TRk3KlSvvfe__47fti9f18qhN_-55qR9--P954fwM-uwHfugHfugH_Bv2KG_TLb9Avv0G__Ab98hv0y2_QL79Bv_wG_fIb9Mtv0C-_Qb_sBv2yG_TLbtAvu0G_9Nb6N_BPb9A3u_E-vbG_7Ab90hv7y27IR3qLfjf4m96Qn-zG-_TW_m7IR_p1f2X8ucu9Y23Lx2o_7vDUujfkaWrT6_GSicK1de3bNXdh527c3OfIPz7qeg31rw5lDJrTuZutTjWWcWLHO_juSONlLqvxeF7lNcjVaJ5u67KGKWs79qm7vtDt5pyGkH0beQ3fkMkzFGX9G_FpLGRM_7_zSY74wufIL5_39nir7ZT_M6IPbzo6yT3GArlJ76wG5Spn0Ys9EV928KI-RHUuKpU9CH2iNH4pHgegSeX36J757TvqmzXnW3yuNX6PaCX37xTUk51pZQ9zRnqX429R1alC5EBNTCqMeRsvxLbSxN0J8_mxOX-We-Bp2wuOECv0zrd8uDrrxshv7mLy-SW-Sb-cZ1Fe9l7Qj8-XP9yENzX6Z-On6jbx4O7sLkTaiyNw39T7Ezs47JvaXoBYu-mnIR4413EL_LzsC5BvF8a4ypWo_k08t4FHfXbJ_vAMRZ7fK_Nj-6PKoVKq_RAdq35nVl7blD-rA2NHd4wd__5YK-2MVTtji4Gx2ZWxQ-vmV8fKXvAEuWTDo_TZmYdtDjX_bHu-rByXnEH_-anXfyJ5jlvZluewajnvXMuYu34eh518rM3bsHZSwlR_yrhci-IqPk5kXtyN2bXdZy0HXPOi58f2WtYHSliYZ-e1HGHOG-_MOXTmcI1ypIeP-r6RVbnGz9LeyV64L-vKnf5bJHPpjr14XizzxlbG0B-Z-5w7vagor_NlL1kWrZ5OiprOyDtOTdwrn2fI4zr3e_bZ8Apjefyfpt_OqV90rPIYLfrgfX5RH7KnezRenv2DfhCdMKexI_SOl32gY_UuLXuzFY-aHh0Ledy9bngQeo3t9oKOvL1hrTq3Ug9x9sm1d5knOYU8X1M_Q_7SygtyHK2O85G7n8Vje93Sy9bFgmm1fT7s7VzuWfaMaYxZVOceA9l7fWzP0VY9bdpXbhV1_k693HV7pqesi5b2JrCqc5ns03PGStvfFVUPvIZnab8BT-YFt-CtNm4PHgusMRuCif13Ya42r9iH3RljVedHO2ttqAfo98fI8wA38Nuse7DYhvpa9tcx2W_QYGz1aaBZxatSn_2qaKCU50a-3xMrzwRcwtvJ3PZ8IL1NqO6XKV_OUieHr-ecMb7WgzI3H_tSH0u5PNM9Pza5-kejq7H6eUjEyS3P1sheldAFbIrauz9oWdjq3NKvZZxrdlFfH7762GRy5K2NANzKxibs5LXnW0EPiqe6Z2VOzZqCxjb5PvLmfYPTh2s0e_4U8bLxDQfNlvGQ3JNW1kzK2p88F1LHemeqgZUxxzyQtJb08I5tPdBPPa13Vvud6506QCzaOoC-LBr67Jt6Xuo5DZ7vh2SS1PFA5efK888bS6OYl-KU5_0r1Y53h5i9IXZSqKZTyt9YOTzVZ5vDgtF5bbIrRjQinHa73dKeLU-Iq7bPgP87czbxThf75dpFfCtITks9LmORsgYytgpenoFCXPtCdc9oCV9EPSovWsuz0ln0W_NC9unGYxV6Az8--739OT-xpn9-2TLZEyv1Qk07_vmdfEt7Lsc-y3PjMl6V-gp7Ymayf0_-R8ZaXqmj8tyC7ZvlGVLwcluf8VLonAwrlmXO4dhKG2eoabW-hP28EMcv8LLqnM8lvKdv4AXu2ApYxzbMSpudIBeoa0mlL6K1i7JeKm16Lp7qb0jWOSvCvOpT1bFmmcctlp_V3KRan3RQ4XXMqtPZbJLFB-odUb-o6Ng84LT9B3CibwFmQzjld-EUVLnxBU7mPTjllX-9Ty4GcVr_fZwqP36Jk3UPThQz5IM45ffgRD7XGsKpuBcnWSP5-zip_wxO_J_BaVCf7sZp9Kc4lTZpXIiFXZ8XaXV9UIau6zriFNjp17Sv61dk6Lqu05nYQp7r6-mFe6de0Ddw1qgXR_0BvQdwkvXyIZzYXTi512RIvwunP7DJ9O0a9pN_4dMgTt_xiVHdf7P84me2d_oZ2fsfW0kfJzZok-_GSf_7OP2JnxnA6Q_8zABOf-JnruB0r58ZxokN2oi7cdL-Pk7WNT59Y5MHcCqu8ek7G3GBU3GVT9dt8hWcBvl0N07DfLobp2E-3YXT-hqflHtwYtNrfFrfg5NyjU8y1rgLp0E-3Y3TMJ_uxmmQT_fjNOhz78PpahyxvQcn9Voc8Z3PvcRpey0WVe_FaZhPd-M0zKe7cRrk0304Bdf4xO_BSbvKp7tsOb_GJ-1enIZzhrtxGswZ7sdpMN67H6fBeO8unPRrdQV2ly13r9UVvothL3EqrsWw7r04Dcfld-M0GMPejdMwn34Dp7IuFRx0r18rSk5-9_7ZyILu_SHu3Sf0XVZ7fm8edmtNPP7ZXfvDXbRjPWepuFozVuW6fPdvWQd2WE793i7c6n0Fx4vdpHtv_7cLV-zN6ryn3f1_DSkLXNKDundVyP53LE4uaOXGu-DZmOdC2zY1Ox7vfrQwWOgteFtHNeT3XsqqfPfmxfLb5VH5HSXzZZ-2Ggs61N-DvXNt_MnLb5mplxmADlpzTk3fKdTXedEl_LPQbOVQ14SdLEH-XNdufa-iF_YfHOq6M-Slep_K71Wqfl3nXGQgqO9fnyvVwPvFMqrhi0Uzv-njicWyrWsvINd1n0hT_fKMn_w2zvc0Vu1nJ7_no_zRTLxzWaOXz5OX_USxHrvy7Jb_H8Lxxu5inXXqDyOLvn-m_ukeaxvbtH2HeDVYK_IbWC1LRff_RiQ7_8uZ0ZYPyU7nzrKRMTcG3uW5xI_eucKY_vdDp48X79rvd42mZ9d8R9ucl02Wp-b7ymQZtNflWYryHDCde6QzxF5JZxl7l_Xjqpf7IXODsq5-bOgY27mr_az_B89b_6zoTml4XvM17_z_AuJN0vk2MKnidXlekXWuT3q1Tv4Sz2qcftRr4n3bx04modWeeQytp871sRnfnptMTnG7DotFr9drx_IMaPesxILJ3nBVH6Jz2nQ-Iy3_V4C045n0l_Qs8GXP4rC34y7fPEPIM4r1t5TuorPnRbtnb0_f8D3UMgKdl3JY9baXafMOesS7uBryrEV9niPoyWHsJ1yjbz07ZzEMpjTfXjuq3vBKy5KD0Z5V_muv_M-__h-xSQJLckkAAA,,",
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