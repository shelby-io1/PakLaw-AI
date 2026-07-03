package com.example.data

data class BookModel(
    val name: String,
    val category: String,
    val sectionsCount: String,
    val imageUrl: String,
    val description: String,
    val sections: List<SectionModel>
)

data class SectionModel(
    val sectionId: String,
    val title: String,
    val excerpt: String,
    val originalText: String,
    val simpleEnglish: String,
    val simpleUrdu: String,
    val readTime: String = "4 min read"
)

data class GlossaryTerm(
    val term: String,
    val definition: String,
    val category: String = "General"
)

object StaticLibraryData {
    // Image constant values matching upstream UI URLs
    const val IMG_CRIMINAL_LAW = "https://lh3.googleusercontent.com/aida-public/AB6AXuA_bGTFK6MhjB7Hcr1ZDZIQKVSl11U_5p8iP8x3YFKkNNH5_wYSsEK_l7nlbeIzHIRb8OYih_Cz6gHG_0SVSxkiSoolD4XQ3NlmDFKQkvL_dnpp1xYk0vO42N6WF38l1Q7c2Txur3a5ZycWvGUS9yC02x-zQ2o1uSu8Ex18VF7CO8Z-24pKnBSchXaHQ6ZUGmt4DfkBdYmueQeVq_JoWyp2L-mVU8Bhb2YDufCBd7TB6Lo-WlgJKX9Y7sU5hUgAkRxzs_7q5FRksNc"
    const val IMG_WOMENS_RIGHTS = "https://lh3.googleusercontent.com/aida-public/AB6AXuAC2vmKWivKNMyk3FH_ioa6kof8UrgEWoiNOuySvHNkzort1YduT68SqWlH7VRJeC7YN47kEYXNu811p7RtwHM-63I7HOHoykb1l2tU__PGAC2oqBgpg896J0EoLIGL75R8EYhrZheHibcg5kq_YSbx7_a8EyqvDdgh66D-oEKQjiSb29XCEpgt8vt3egq1VN4BdsameB65c-rtGD00_yqgcKjdAOz5QQMUWDpO8mgZfxTrG2WKeCtcUwGGRMqQcYBAE_-mLJX0mgM"
    const val IMG_CYBER_LAW = "https://lh3.googleusercontent.com/aida-public/AB6AXuCuxQ-hddES2vEXRshNVA0JchbB1OmGeYcBVFcB3bDEy6tTW6PgFzI5hBE-Q3kXp1bxB6cjf88JwE9NQtdyyFl8e_q7Et0_Ys7Ls3w_iuNA6JPxnkuo6LOomZdhK4zlhyY_RA-AcbxkD_IHymXQbWnyTvHZiMGZWUrgsElTvH7X1rxEAdf1oMXs5M3eOxIS1kxEsovSF37_OyMYqbVRYg5PDwny_lnOqj-AOZSIhZLLW_8FJW1Si7CaE-cK4BEdIykPfNu3oyL_144"
    const val IMG_SCALES = "https://lh3.googleusercontent.com/aida-public/AB6AXuB08D5FovJv66HykDvqqwFjBPaf8L8Gao4QxsAomRZxDWvsjirOjLobbVtXT4gtGkcsgMSknYijAdbcdRQbIju-IezGYTH6SDsC62PGFG8UcRLC_YUEZ-oOHfYQCltmODtqhhToUyM7Ijjpvefz8xedwtYFp7VgLpgQIX6xQQYuSPQDM4Kd1yKllZ64Fa1No8WVSO0p2fppJbPiXWIKYHP66nV06cp6mxTcPv8ut-02DIhAGJV4EitAYqrbYfXAeUdkS4EWaHbFXFg"

    val listBooks = listOf(
        BookModel(
            name = "Pakistan Penal Code (PPC 1860)",
            category = "Criminal Law",
            sectionsCount = "511 Sections",
            imageUrl = IMG_CRIMINAL_LAW,
            description = "The comprehensive penal code containing main statutory offenses and punishments in Pakistani criminal law jurisdiction.",
            sections = listOf(
                SectionModel(
                    sectionId = "ppc_302",
                    title = "Section 302: Punishment of Qatl-i-amd",
                    excerpt = "Penalties and specifications for intentional killing.",
                    originalText = "Whoever commits qatl-i-amd shall, subject to the provisions of this Chapter, be: (a) punished with death as qisas; (b) punished with death or imprisonment for life as ta'zir...",
                    simpleEnglish = "Section 302 details punishments for committing intentional murder (Qatl-i-Amd) in Pakistan. The court can sentence the offender to death (as qisas or ta'zir) or life imprisonment depending on evidence.",
                    simpleUrdu = "دفعہ 302 قصداً قتل (قتلِ عمد) کی سزا بیان کرتی ہے۔ اس دفعہ کے تحت مجرم کو سزائے موت (قصاص یا تعزیر کے طور پر) یا عمر قید کی سزا دی جا سکتی ہے۔"
                ),
                SectionModel(
                    sectionId = "ppc_379",
                    title = "Section 379: Punishment for Theft",
                    excerpt = "Punishment for stealing physical property.",
                    originalText = "Whoever commits theft shall be punished with imprisonment of either description for a term which may extend to three years, or with fine, or with both.",
                    simpleEnglish = "Section 379 states that anyone who steals another person's property without their consent will be punished with jail time of up to three years, a fine, or both.",
                    simpleUrdu = "دفعہ 379 چوری کی سزا مقرر کرتی ہے۔ اس کے مطابق چوری کرنے والے شخص کو تین سال تک قید، جرمانہ یا دونوں سزائیں ایک ساتھ دی جا سکتی ہیں۔"
                ),
                SectionModel(
                    sectionId = "ppc_419",
                    title = "Section 419: Punishment for Cheating by Impersonation",
                    excerpt = "Punishment for pretending to be someone else to cheat.",
                    originalText = "Whoever commits cheating by impersonation shall be punished with imprisonment of either description for a term which may extend to three years, or with fine, or with both.",
                    simpleEnglish = "Section 419 punishes anyone who deceives or cheats a person by pretending to be someone else. The penalty is up to three years in prison, a fine, or both.",
                    simpleUrdu = "دفعہ 419 بھیس بدل کر یا دھوکہ دہی سے کسی اور کی جگہ پیش ہونے کی سزا مقرر کرتی ہے۔ اس کے تحت تین سال تک قید یا جرمانہ کی سزا ہو سکتی ہے۔"
                )
            )
        ),
        BookModel(
            name = "Constitution of Pakistan 1973",
            category = "Civil Law",
            sectionsCount = "280 Articles",
            imageUrl = IMG_SCALES,
            description = "The supreme law of Pakistan defining the federal republic system, fundamental rights of citizens, and governance institutions.",
            sections = listOf(
                SectionModel(
                    sectionId = "const_4",
                    title = "Article 4: Right of individuals to be dealt with in accordance with law",
                    excerpt = "To enjoy the protection of law is the inalienable right of every citizen.",
                    originalText = "To enjoy the protection of law and to be treated in accordance with law is the inalienable right of every citizen, wherever he may be, and of every other person for the time being within Pakistan.",
                    simpleEnglish = "Article 4 is a pillar of the Constitution, guaranteeing that every citizen must be treated strictly in accordance with written law. No action detrimental to life, liberty, or property can be taken without legal authorization.",
                    simpleUrdu = "آرٹیکل 4 آئینِ پاکستان کا ستون ہے جو اس بات کی ضمانت دیتا ہے کہ ہر شہری کے ساتھ قانون کے مطابق سلوک کیا جائے گا۔ کسی بھی شہری کی جان، مال یا آزادی کو غیر قانونی طور پر نقصان نہیں پہنچایا جا سکتا۔"
                ),
                SectionModel(
                    sectionId = "const_9",
                    title = "Article 9: Security of person",
                    excerpt = "No person shall be deprived of life or liberty.",
                    originalText = "No person shall be deprived of life or liberty save in accordance with law.",
                    simpleEnglish = "Article 9 guarantees the fundamental right to life and personal safety. It declares that no individual's life or personal freedom can be taken away unless authorized by a specific valid law.",
                    simpleUrdu = "آرٹیکل 9 انسانی جان اور ذاتی آزادی کے بنیادی حق کا تحفظ کرتا ہے۔ اس کے مطابق قانون کی اجازت کے بغیر کسی بھی شخص کو زندگی یا ذاتی آزادی سے محروم نہیں کیا جا سکتا۔"
                ),
                SectionModel(
                    sectionId = "const_19a",
                    title = "Article 19A: Right to Information",
                    excerpt = "Every citizen has the right to access public information.",
                    originalText = "Every citizen shall have the right to have access to information in all matters of public importance subject to regulation and reasonable restrictions imposed by law.",
                    simpleEnglish = "Article 19A provides every Pakistani citizen the constitutional right to access information of public interest, ensuring transparency in governance and government departments.",
                    simpleUrdu = "آرٹیکل 19-اے پاکستان کے ہر شہری کو عوامی اہمیت کی معلومات تک رسائی کا آئینی حق دیتا ہے تاکہ حکومتی امور میں شفافیت برقرار رہے۔"
                )
            )
        ),
        BookModel(
            name = "PECA 2016 (Cyber Crime)",
            category = "Cyber Law",
            sectionsCount = "55 Sections",
            imageUrl = IMG_CYBER_LAW,
            description = "Prevention of Electronic Crimes Act governing hacking, online fraud, social media harassment, and cyber security regulations.",
            sections = listOf(
                SectionModel(
                    sectionId = "peca_3",
                    title = "Section 3: Unauthorized Access to Information System",
                    excerpt = "Punishment for unauthorized hacking of computers.",
                    originalText = "Whoever intentionally gains unauthorized access to any information system or data shall be punished with imprisonment for a term which may extend to three months or with fine which may extend to fifty thousand rupees or both.",
                    simpleEnglish = "Section 3 punishes unauthorized computer hacking. Intentionally accessing someone else's computer, server, or digital system without permission carries up to 3 months in prison or a fifty-thousand rupees fine.",
                    simpleUrdu = "دفعہ 3 کمپیوٹر ہیکنگ یا غیر متعلقہ رسائی کو جرم قرار دیتی ہے۔ کسی کی اجازت کے بغیر اس کے ڈیٹا یا سسٹم تک رسائی پر تین ماہ تک قید یا پچاس ہزار روپے تک جرمانہ ہو سکتا ہے۔"
                ),
                SectionModel(
                    sectionId = "peca_20",
                    title = "Section 20: Dignity of natural person",
                    excerpt = "Online defamation or sharing false personal media.",
                    originalText = "Whoever intentionally and publicly exhibits or transmits any information which he knows to be false, and intimates or harms the reputation or privacy of a natural person, shall be punished with imprisonment for a term which may extend to three years or with fine which may extend to one million rupees or both.",
                    simpleEnglish = "Section 20 criminalizes online harassment and defamation. Anyone who spreads false, damaging rumors or private images of another person on social media can face up to 3 years in prison, up to 1 million PKR fine, or both.",
                    simpleUrdu = "دفعہ 20 سوشل میڈیا یا آن لائن ہراسانی اور بدنامی کے خلاف کارروائی کا حق دیتی ہے۔ کسی شخص کے خلاف جھوٹی معلومات یا نجی تصاویر پھیلانے پر تین سال تک قید اور دس لاکھ روپے تک جرمانہ ہو سکتا ہے۔"
                )
            )
        ),
        BookModel(
            name = "Muslim Family Laws Ordinance 1961",
            category = "Civil Law",
            sectionsCount = "13 Sections",
            imageUrl = IMG_WOMENS_RIGHTS,
            description = "Primary civil ordinance regulating Nikah registration, polygamy approvals, Talaq notices, and maintenance arbitration.",
            sections = listOf(
                SectionModel(
                    sectionId = "mflo_6",
                    title = "Section 6: Polygamy Regulation",
                    excerpt = "Requirement of permission for subsequent marriage.",
                    originalText = "No man, during the subsistence of an existing marriage, shall, except with the previous permission in writing of the Arbitration Council, contract another marriage...",
                    simpleEnglish = "Section 6 establishes that a man must obtain formal written permission from the governmental Arbitration Council and his existing wife before contracting a second marriage. Violating this is a criminal offense.",
                    simpleUrdu = "دفعہ 6 دوسرے نکاح کے ضابطے بیان کرتی ہے۔ اس قانون کے تحت شوہر کو دوسری شادی کے لیے ثالثی کونسل اور پہلی بیوی سے تحریری اجازت نامہ حاصل کرنا ضروری ہے، بصورت دیگر یہ جرم ہوگا۔"
                ),
                SectionModel(
                    sectionId = "mflo_7",
                    title = "Section 7: Talaq Notice Procedure",
                    excerpt = "Cooling-off and reconciliation notice path.",
                    originalText = "Any man who wishes to divorce his wife shall, as soon as may be after the pronouncement of talaq in any form whatsoever, give the Chairman notice in writing of his having done so...",
                    simpleEnglish = "Section 7 mandates that immediately after pronouncing divorce, the husband must send a written notice to the Chairman of the local Union Council, with a copy to the wife. A 90-day cooling-off reconciliation period is initiated.",
                    simpleUrdu = "دفعہ 7 طلاق کے نوٹس کا طریقہ کار لازمی قرار دیتی ہے۔ شوہر طلاق دینے کے بعد مقامی یونین کونسل کے چیئرمین کو تحریری نوٹس بھیجنے اور بیوی کو اس کی نقل فراہم کرنے کا پابند ہے، جس کے بعد 90 دن کا مصالحتی وقت شروع ہوتا ہے۔"
                )
            )
        )
    )

    val glossaryTerms = listOf(
        GlossaryTerm(
            term = "CrPC (Code of Criminal Procedure)",
            definition = "The systematic procedural code governing the investigation, policing, criminal arrest, trial procedures, court setups, and appeal pathways for all criminal categories in Pakistan.",
            category = "Criminal Procedural Law"
        ),
        GlossaryTerm(
            term = "PPC (Pakistan Penal Code)",
            definition = "The core codification of criminal liabilities, definitions of offenses (murder, theft, fraud, defalcation), specific punishments, and statutory boundaries.",
            category = "Criminal Substantive Law"
        ),
        GlossaryTerm(
            term = "Habeas Corpus",
            definition = "A highly critical judicial writ (usually filed under Section 491 of the CrPC or Article 199 of the Constitution) order to produce an unlawfully arrested or detained person before the court, ensuring personal liberty from arbitrary state/private imprisonment.",
            category = "Constitutional Writs"
        ),
        GlossaryTerm(
            term = "FIR (First Information Report)",
            definition = "The official initial report filed by a citizen or police officer under Section 154 of the CrPC. It launches police investigations into non-bailable, cognizable societal crimes.",
            category = "Criminal Procedure"
        ),
        GlossaryTerm(
            term = "Bail",
            definition = "The conditional release of an accused person from police custody pending final judicial verdict, in exchange for surety/bonds, protecting their presumption of innocence prior to guilt declaration.",
            category = "Judicial Remedies"
        ),
        GlossaryTerm(
            term = "Qisas",
            definition = "Equal, proportionate retribution in Islamic criminal jurisprudence enacted within the PPC, granting victims or their rightful legal heirs the literal option to demand reciprocal punishment.",
            category = "Islamic Law"
        ),
        GlossaryTerm(
            term = "Ta'zir",
            definition = "Discretionary jail sentences, penal fines, or corporate constraints awarded by judges under the PPC for criminal offenses where absolute Qisas is either legally inapplicable or waived.",
            category = "Islamic Law"
        ),
        GlossaryTerm(
            term = "Section 144",
            definition = "A legislative emergency power under the Code of Criminal Procedure empowering local administration (District Magistrates) to immediately restrict crowds, gatherings of four or more individuals, or other risky activities to protect generic public safety.",
            category = "Public Order"
        ),
        GlossaryTerm(
            term = "Nikah & Talaq",
            definition = "The solemn Islamic civil contract for marriage verification (Nikah) and the legally regulated judicial/Union-council methods to process divorce notifications (Talaq) as stated under Muslim Family Law rules.",
            category = "Family Civil Law"
        )
    )
}
