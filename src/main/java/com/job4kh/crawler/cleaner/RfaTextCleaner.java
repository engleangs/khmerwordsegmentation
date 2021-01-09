package com.job4kh.crawler.cleaner;

public class RfaTextCleaner implements NewsTextCleaner{
    static final String start = "\\.mp3";
    static final String end = "៕";
    @Override
    public String clean(String text) {
        String [] first = text.split(start);
        if( first.length > 1){
            String content = first[1];
            String[] finalStr = content.split(end);
            return finalStr[0];
        }
        return text;
    }
    public static void main(String[]args){
        System.out.println("test clean rfa text");
        RfaTextCleaner rfaTextCleaner = new RfaTextCleaner();
        String txt = "អង្គការ\u200B៤\u200Bគ្រោង\u200Bធ្វើ\u200Bសិក្ខាសាលា\u200Bស្ដី\u200Bអំពី\u200Bហិរញ្ញវត្ថុ\u200Bនយោបាយ — ខ្មែរ អំពី RFA Search Advanced Search… Toggle navigation ខ្មែរ បទវិភាគ នយោបាយ សិទ្ធិមនុស្ស សង្គម សេដ្ឋកិច្ច ដីធ្លី បរិស្ថាន សុខភាព ប្រវត្តិសាស្ត្រ ច្បាប់ ខ្មែរ\u200Bក្រហម រូបភាព វីដេអូ វេទិកា\u200Bអ្នក\u200Bស្ដាប់ នាទី\u200Bប្រចាំ\u200Bសប្ដាហ៍ អំពី RFA ខ្មែរ | ព័ត៌មាន អង្គការ\u200B៤\u200Bគ្រោង\u200Bធ្វើ\u200Bសិក្ខាសាលា\u200Bស្ដី\u200Bអំពី\u200Bហិរញ្ញវត្ថុ\u200Bនយោបាយ អង្គការ\u200Bសង្គម\u200Bស៊ីវិល\u200Bកំពុង\u200Bធ្វើ\u200Bសេចក្ដី\u200Bព្រាង\u200Bច្បាប់\u200Bសម្រាប់\u200Bត្រួត\u200Bពិនិត្យ\u200Bការ\u200Bចំណាយ\u200Bរបស់\u200Bគណបក្ស\u200Bនយោបាយ\u200Bពេល\u200Bឃោសនា\u200Bបោះ\u200Bឆ្នោត\u200Bនៅ\u200Bពេល\u200Bខាង\u200Bមុខ ដោយ\u200Bគេ\u200Bជំរុញ\u200Bឲ្យ\u200Bរដ្ឋាភិបាល\u200Bទទួល\u200Bយក\u200Bសេចក្ដី\u200Bព្រាង\u200Bច្បាប់\u200Bនេះ\u200Bឲ្យ\u200Bសភា\u200Bអនុម័ត។ ដោយ ទីន ហ្សាការីយ៉ា 2013-02-17 Tweet បោះពុម្ព ចែករំលែក មតិ អ៊ីម៉េល\u200B zr.mp3 ការ\u200Bធ្វើ\u200Bសេចក្ដី\u200Bព្រាង\u200Bច្បាប់\u200Bស្ដីពី\u200Bហិរញ្ញវត្ថុ\u200Bនយោបាយ\u200Bសម្រាប់\u200Bការ\u200Bឃោសនា\u200Bបោះ\u200Bឆ្នោត\u200Bនេះ ជា\u200Bផ្នែក\u200Bមួយ\u200Bដ៏\u200Bសំខាន់\u200Bដើម្បី\u200Bឲ្យ\u200Bការ\u200Bបោះ\u200Bឆ្នោត\u200Bនៅ\u200Bឆ្នាំ\u200B២០១៣ កាន់\u200Bតែ\u200Bសុក្រឹត និង\u200Bមាន\u200Bតម្លាភាព\u200Bសម្រាប់\u200Bគ្រប់\u200Bគណបក្ស\u200Bនយោបាយ\u200Bដែល\u200Bចូល\u200Bរួម\u200Bប្រកួត\u200Bប្រជែង\u200Bទាំង\u200Bអស់។ អង្គការ\u200Bសង្គម\u200Bស៊ីវិល\u200Bចំនួន ៤ គ្រោង\u200Bនឹង\u200Bធ្វើ\u200Bសិក្ខាសាលា\u200Bនៅ\u200Bចុង\u200Bខែ\u200Bកុម្ភៈ និង\u200Bនៅ\u200Bដើម\u200Bខែ\u200Bមីនា ខាង\u200Bមុខ ស្ដីពី\u200Bហិរញ្ញវត្ថុ\u200Bនយោបាយ\u200Bសម្រាប់\u200Bការ\u200Bឃោសនា\u200Bបោះ\u200Bឆ្នោត មាន\u200Bម្ចាស់\u200Bឆ្នោត និង\u200Bអ្នក\u200Bតំណាងរាស្ត្រ\u200Bចូល\u200Bរួម ដែល\u200Bគេ\u200Bធ្វើ\u200Bឡើង\u200Bនៅ\u200Bខែត្រ\u200Bព្រះសីហនុ និង\u200Bនៅ\u200Bខែត្រ\u200Bតាកែវ។ ប្រធាន\u200Bគណៈកម្មាធិការ\u200Bអព្យាក្រឹត្យ និង\u200Bយុត្តិធម៌ ដើម្បី\u200Bការ\u200Bបោះ\u200Bឆ្នោត\u200Bដោយ\u200Bសេរី\u200Bនៅ\u200Bកម្ពុជា ហៅ\u200Bថា និចហ្វិក (NICFEC) លោក\u200Bបណ្ឌិត ហង្ស ពុទ្ធា បាន\u200Bឲ្យ\u200Bដឹង\u200Bនៅ\u200Bថ្ងៃ\u200Bទី\u200B១៧ កុម្ភៈ ថា ការ\u200Bរៀបចំ\u200Bសិក្ខាសាលា\u200Bស្ដីពី\u200Bហិរញ្ញវត្ថុ\u200Bនយោបាយ\u200Bសម្រាប់\u200Bការ\u200Bឃោសនា\u200Bបោះ\u200Bឆ្នោត\u200Bនេះ ដើម្បី\u200Bស្វែង\u200Bរក\u200Bការ\u200Bគាំទ្រ\u200Bពី\u200Bប្រជាពលរដ្ឋ\u200Bដែល\u200Bជា\u200Bម្ចាស់\u200Bឆ្នោត និង\u200Bអ្នក\u200Bនយោបាយ ពីព្រោះ\u200Bថា សេចក្ដី\u200Bពា្រង\u200Bច្បាប់\u200Bនេះ\u200Bមាន\u200Bសារសំខាន់\u200Bណាស់\u200Bក្នុង\u200Bការ\u200Bកាត់\u200Bបន្ថយ\u200Bអំពើ\u200Bពុករលួយ និង\u200Bការ\u200Bប្រើប្រាស់\u200Bថវិកា\u200Bរបស់\u200Bគណបក្ស\u200Bនយោបាយ\u200Bដែល\u200Bគ្មាន\u200Bប្រភព\u200Bចំណូល\u200Bច្បាស់\u200Bលាស់។ អង្គការ\u200Bចំនួន ៤ សហការ\u200Bគ្នា គ្រោង\u200Bនឹង\u200Bធ្វើ\u200Bសិក្ខាសាលា\u200Bស្ដីពី\u200Bហិរញ្ញវត្ថុ\u200Bនយោបាយ\u200Bសម្រាប់\u200Bការ\u200Bឃោសនា\u200Bបោះ\u200Bឆ្នោត រួម\u200Bមាន គណៈកម្មាធិការ\u200Bដើម្បី\u200Bការ\u200Bបោះ\u200Bឆ្នោត\u200Bដោយ\u200Bសេរី និង\u200Bយុត្តិធម៌\u200Bនៅ\u200Bកម្ពុជា គណៈកម្មាធិការ\u200Bអព្យាក្រឹត និង\u200Bយុត្តិធម៌ ដើម្បី\u200Bការ\u200Bបោះ\u200Bឆ្នោត\u200Bដោយ\u200Bសេរី\u200Bនៅ\u200Bកម្ពុជា អង្គការ ដ្រាក និង\u200Bសម្ព័ន្ធ\u200Bដើម្បី\u200Bសុចរិត\u200Bភាព និង\u200Bគណនេយ្យ\u200Bសង្គម។ សិក្ខាសាលា\u200Bនេះ\u200Bនឹង\u200Bធ្វើ\u200Bឡើង\u200Bនៅ\u200Bខែត្រ\u200Bតាកែវ នៅ\u200Bថ្ងៃ\u200Bទី\u200B២៦ កុម្ភៈ និង\u200Bនៅ\u200Bខែត្រ\u200Bព្រះសីហនុ នៅ\u200Bថ្ងៃ\u200Bទី\u200B៤ មីនា ឆ្នាំ\u200B២០១៣។ លោក\u200Bបណ្ឌិត ហង្ស ពុទ្ធា បាន\u200Bសង្កត់\u200Bធ្ងន់\u200Bថា សេចក្ដី\u200Bព្រាង\u200Bច្បាប់\u200Bនេះ នឹង\u200Bបញ្ជូន\u200Bទៅ\u200Bរដ្ឋាភិបាល\u200Bដើម្បី\u200Bសុំ\u200Bការ\u200Bគាំទ្រ\u200Bពី\u200Bរដ្ឋាភិបាល ដើម្បី\u200Bផ្ញើ\u200Bទៅ\u200Bសភា\u200Bអនុម័ត។ មួយ\u200Bវិញ\u200Bទៀត អង្គការ\u200Bសង្គម\u200Bស៊ីវិល\u200Bក៏\u200Bស្វែង\u200Bរក\u200Bការ\u200Bគាំទ្រ\u200Bពី\u200Bសភា\u200Bដោយ\u200Bផ្ទាល់\u200Bដែរ ដើម្បី\u200Bធ្វើ\u200Bយ៉ាង\u200Bណា\u200Bឲ្យ\u200Bសភា\u200Bទទួល\u200Bយក និង\u200Bអនុម័ត\u200Bច្បាប់\u200Bនេះ\u200Bនៅ\u200Bមុន\u200Bពេល\u200Bបោះ\u200Bឆ្នោត\u200Bឆ្នំា\u200B២០១៣។ តំណាងរាស្ត្រ\u200Bគណបក្ស សម រង្ស៊ី លោក សុន ឆ័យ បាន\u200Bគាំទ្រ\u200Bចំពោះ\u200Bសេចក្ដី\u200Bព្រាង\u200Bច្បាប់\u200Bនេះ។ ដោយ\u200Bលោក\u200Bបាន\u200Bលើក\u200Bហេតុផល\u200Bថា ច្បាប់\u200Bស្ដី\u200Bពី\u200Bហិរញ្ញវត្ថុ\u200Bគណបក្ស\u200Bនយោបាយ\u200Bឃោសនា\u200Bបោះ\u200Bឆ្នោត\u200Bនេះ មាន\u200Bសារសំខាន់\u200Bណាស់\u200Bក្នុង\u200Bការ\u200Bធានា\u200Bដល់\u200Bលទ្ធផល\u200Bបោះ\u200Bឆ្នោត។ មជ្ឈមណ្ឌល\u200Bសិទ្ធិ\u200Bមនុស្ស\u200Bកម្ពុជា បាន\u200Bចេញ\u200Bផ្សាយ\u200Bកំណត់\u200Bត្រា\u200Bសង្ខេប\u200Bរបស់\u200Bខ្លួន\u200Bកាល\u200Bពី\u200Bសប្ដាហ៍\u200Bមុន ស្ដីពី\u200Bកំណែ\u200Bទម្រង់\u200Bនយោបាយ និង\u200Bប្រព័ន្ធ\u200Bបោះ\u200Bឆ្នោត\u200Bសម្រាប់\u200Bកម្ពុជា។ នៅ\u200Bក្នុង\u200Bកំណត់\u200Bត្រា\u200Bនេះ ក៏\u200Bបាន\u200Bសរសេរ\u200Bអំពី\u200Bគម្លាត\u200Bខុស\u200Bគ្នា\u200Bរវាង\u200Bគណបក្ស\u200Bកាន់\u200Bអំណាច និង\u200Bគណបក្ស\u200Bដទៃ\u200Bទៀត\u200Bក្នុង\u200Bការ\u200Bប្រើប្រាស់\u200Bថវិកា\u200Bនៅ\u200Bពេល\u200Bឃោសនា\u200Bបោះ\u200Bឆ្នោត គឺ\u200Bគណបក្ស\u200Bប្រជាជន\u200Bកម្ពុជា បាន\u200Bចំណាយ\u200Bលុយ\u200Bច្រើន\u200Bលើស\u200Bលុប\u200Bនៅ\u200Bក្នុង\u200Bពេល\u200Bយុទ្ធនា\u200Bការ\u200Bបោះ\u200Bឆ្នោត។ អង្គការ\u200Bខុមហ្វ្រែល បាន\u200Bរក\u200Bឃើញ\u200Bថា នៅ\u200Bក្នុង\u200Bយុទ្ធនាការ\u200Bឃោសនា\u200Bបោះ\u200Bឆ្នោត\u200Bជ្រើស\u200Bរើស\u200Bក្រុមប្រឹក្សា\u200Bឃុំ សង្កាត់ កាល\u200Bពី\u200Bឆ្នាំ\u200B២០១២ នោះ គណបក្ស\u200Bប្រជាជន\u200Bកម្ពុជា បាន\u200Bប្រើ\u200Bថវិកា\u200Bច្រើន\u200Bជាង\u200Bគេ គឺ\u200Bការ\u200Bចំណាយ\u200Bក្នុង\u200Bការ\u200Bឃោសនា\u200Bនៅ\u200Bមូលដ្ឋាន\u200Bម្តង បាន\u200Bចំណាយ\u200Bថវិកា\u200Bប្រមាណ ៩\u200Bពាន់\u200Bដុល្លារ។ គណបក្ស សម រង្ស៊ី ចំណាយ ១.៥០០\u200Bដុល្លារ គណបក្ស\u200Bសិទ្ធិ\u200Bមនុស្ស ចំណាយ ៣០០\u200Bដុល្លារ គណបក្ស នរោត្តម រណឫទ្ធិ ចំណាយ ២០០\u200Bដុល្លារ និង\u200Bគណបក្ស\u200Bហ៊្វុនស៊ិនប៉ិច ចំណាយ\u200Bតិច\u200Bជាង ២០០\u200Bដុល្លារ\u200Bក្នុង\u200Bមួយ\u200Bដង\u200Bនៃ\u200Bយុទ្ធនាការ\u200Bឃោសនា\u200Bនៅ\u200Bមូលដ្ឋាន។ ប៉ុន្តែ\u200Bទោះ\u200Bបី\u200Bជា\u200Bយ៉ាង\u200Bនេះ\u200Bក្ដី សេចក្ដី\u200Bព្រាង\u200Bច្បាប់\u200Bនេះ\u200Bគេ\u200Bមិន\u200Bទាន់\u200Bដឹង\u200Bនៅ\u200Bឡើយ\u200Bទេ\u200Bថា សភា\u200Bនឹង\u200Bទទួល\u200Bយក និង\u200Bអនុម័ត\u200Bឲ្យ\u200Bបាន\u200Bមុន\u200Bពេល\u200Bបោះ\u200Bឆ្នោត\u200Bអាណត្តិ\u200Bទី\u200B៥ នៅ\u200Bខែ\u200Bកក្កដា ឆ្នាំ\u200B២០១៣ នេះ ឬ\u200Bយ៉ាង\u200Bណា\u200Bនៅ\u200Bឡើយ\u200Bទេ៕ កំណត់ចំណាំចំពោះអ្នកបញ្ចូលមតិនៅក្នុងអត្ថបទនេះ៖ ដើម្បី\u200Bរក្សា\u200Bសេចក្ដី\u200Bថ្លៃថ្នូរ យើង\u200Bខ្ញុំ\u200Bនឹង\u200Bផ្សាយ\u200Bតែ\u200Bមតិ\u200Bណា ដែល\u200Bមិន\u200Bជេរ\u200Bប្រមាថ\u200Bដល់\u200Bអ្នក\u200Bដទៃ\u200Bប៉ុណ្ណោះ។ អត្ថបទ\u200Bដែល\u200Bទាក់ទង សង្គម\u200Bស៊ីវិល\u200Bច្រាន\u200Bចោល\u200Bការ\u200Bចោទ\u200Bប្រកាន់\u200Bថា ពួកគេ\u200B\u200Bនៅ\u200Bពីក្រោយ\u200Bខ្នង\u200Bនៃ\u200Bការ\u200Bតវ៉ា\u200Bរបស់\u200Bពលរដ្ឋ រដ្ឋាភិបាល\u200Bស៊ុយអែត\u200Bសម្រេច\u200Bបិទ\u200Bស្ថានទូត\u200Bនៅ\u200Bកម្ពុជា\u200Bនៅ\u200Bចុង\u200Bឆ្នាំ\u200B២០២១ មតិ\u200Bពលរដ្ឋ\u200Bជុំវិញ\u200Bការ\u200Bលើក\u200Bពេល\u200Bសវនាការ\u200Bជម្រះក្ដី\u200Bសកម្មជន និង\u200Bអ្នក\u200Bគាំទ្រ\u200Bបក្ស\u200Bប្រឆាំង មន្ត្រី\u200Bបក្ស\u200Bប្រឆាំង\u200Bស្នើ\u200Bលោក ហ៊ុន សែន បញ្ឈប់\u200Bវប្បធម៌\u200Bលាប\u200Bពណ៌ និង\u200Bឈ្លប\u200Bយកការណ៍\u200Bបែប\u200Bខ្មែរក្រហម លោក ហ៊ុន សែន ប្ដេជ្ញា\u200Bការពារ\u200Bត្រកូល\u200Bហ៊ុន ទោះ\u200Bបី\u200Bជា\u200Bរលាយ\u200Bផែនដី\u200Bក៏ដោយ តុលាការ\u200Bសម្រេច\u200Bលើក\u200Bពេល\u200Bជំនុំជម្រះ\u200Bរឿង\u200Bក្ដី\u200Bមន្ត្រី និង\u200Bសកម្មជន\u200Bបក្ស\u200Bប្រឆាំង ទៅ\u200Bដើម\u200Bឆ្នាំ\u200B២០២១ លោក ថាច់ សេដ្ឋា ប្រកាស\u200Bជំហរ\u200Bថា លោក\u200Bនឹង\u200Bចូលខ្លួន\u200Bទៅ\u200Bបំភ្លឺ\u200Bតាម\u200Bការ\u200Bកោះហៅ\u200Bរបស់\u200Bតុលាការ មន្ត្រី\u200Bបក្ស\u200Bប្រឆាំង\u200Bស្នើ\u200Bឱ្យ\u200Bបញ្ឈប់\u200Bសាងសង់\u200Bស្តូប\u200Bមិត្តភាព\u200Bកម្ពុជា\u200Bវៀតណាម លោក\u200Bមេធាវី\u200B សំ សុគង់ ថា ការ\u200Bចាត់\u200Bតាំង\u200Bមេធាវី\u200Bនៅ\u200Bកៀក\u200Bថ្ងៃ\u200Bសវនាការ គឺ\u200Bជា\u200Bការ\u200Bធ្វើ\u200Bគ្រាន់\u200Bតែ\u200Bបង្គ្រប់\u200Bកិច្ច\u200Bប៉ុណ្ណោះ ប្រពន្ធ\u200Bសកម្មជន\u200Bបក្ស\u200Bប្រឆាំង\u200B\u200Bម្នាក់\u200Bស្លុត\u200Bចិត្ត\u200Bបន្ទាប់\u200Bពី\u200Bចូល\u200Bសួរសុខទុក្ខ\u200Bឃើញ\u200B\u200Bប្ដី\u200Bធ្លាក់\u200Bខ្លួន\u200Bឈឺ\u200Bនៅ\u200Bពន្ធនាគារ\u200Bព្រៃ\u200Bស ព័ត៌មាន (0) ចុចត្រង់នេះដើម្បីបន្ថែមយោបល់របស់អ្នក បោះពុម្ព ចែករំលែក អ៊ីម៉េល\u200B មើល\u200Bមតិ\u200Bទាំង\u200Bអស់. ស្ដាប់ផ្ទាល់ (ព្រឹក ៥:៣០-៦:៣០ ល្ងាច ៧:៣០-៨:៣០) សំឡេង តើ\u200Bលោក ហ៊ុន សែន និង\u200Bគណបក្ស\u200Bប្រជាជន\u200Bកម្ពុជា\u200Bកំពុង\u200Bជួប\u200Bវិបត្តិ\u200Bផ្ទៃ\u200Bក្នុង\u200Bបែប\u200Bណា? ក្រុមយុវជន និង\u200Bពលរដ្ឋ\u200Bខេត្តតាកែវ ស្នើឱ្យ\u200Bអាជ្ញាធរ\u200Bទប់\u200Bស្កាត់\u200Bជនជាតិ\u200Bវៀតណាម\u200Bចូល\u200Bកម្ពុជា\u200Bដោយ\u200Bខុសច្បាប់ គណបក្ស\u200Bកាន់អំណាច\u200Bកំណត់\u200Bអាទិភាព\u200Bរឿង\u200Bចុះ\u200Bដោះស្រាយ\u200Bបញ្ហា\u200Bពលរដ្ឋ និង\u200Bរៀបចំ\u200Bអ្នក\u200Bបន្តវេន “ការ\u200Bរំលាយ\u200Bបក្ស\u200Bប្រឆាំង\u200Bធ្វើ\u200Bឱ្យ\u200Bកម្ពុជា\u200Bលំបាក\u200Bរាប់\u200Bឆ្នាំ\u200Bទៅ\u200Bមុខ\u200Bទៀត បើ\u200Bគ្មាន\u200Bដំណោះស្រាយ\u200Bផ្ទៃ\u200Bក្នុង” ពលករ\u200Bម្នាក់\u200Bបាន\u200Bស្លាប់\u200Bក្នុង\u200Bអំឡុង\u200Bពេល\u200Bធ្វើ\u200Bចត្តាឡីស័ក\u200Bរយៈ\u200Bពេល\u200B១៤\u200B ថ្ងៃ\u200B នៅ\u200Bខេត្ត\u200Bត្បូងឃ្មុំ អគ្គលេខាធិការ\u200Bអង្គការ\u200Bសហប្រជាជាតិ អំពាវនាវ\u200Bដល់\u200Bអាជ្ញាធរ\u200Bរដ្ឋ\u200Bឈប់\u200Bការ\u200Bបំភិតបំភ័យ\u200Bប្រឆាំង\u200Bនឹង\u200Bអ្នក\u200Bធ្វើ\u200Bការ\u200Bជាមួយ UN អត្ថបទសម្រាំង «របប\u200Bក្រុងភ្នំពេញ\u200Bនឹង\u200Bជួប\u200Bទណ្ឌកម្ម\u200Bធ្ងន់ធ្ងរ\u200B បើ\u200Bជ្រើសរើស\u200Bយក\u200Bការ\u200Bបង្ហូរ\u200Bឈាម \u200Bនៅ\u200Bថ្ងៃទី\u200B៩\u200Bខែវិច្ឆិកា\u200B» សហរដ្ឋ\u200Bអាមេរិក\u200Bអំពាវនាវ\u200Bជា\u200Bថ្មី\u200Bទៀត ឱ្យ\u200Bមាន\u200Bការ\u200Bដោះលែង\u200Bលោក កឹម សុខា លិខិត\u200Bចំហ\u200Bជូន\u200Bបណ្ឌិត កែម ឡី ក្នុង\u200Bថ្ងៃ\u200Bខួប\u200Bគម្រប់ ៣ឆ្នាំ បញ្ចេញ\u200Bមតិយោបល់៖ បញ្ចូលមតិរបស់អ្នកដោយបំពេញទម្រង់ខាងក្រោមជាអក្សរសុទ្ធ។ មតិនឹងត្រូវសម្រេចដោយអ្នកសម្របសម្រួល និងអាចពិនិត្យកែប្រែឲ្យស្របតាម លក្ខខណ្ឌនៃការប្រើប្រាស់ របស់វិទ្យុអាស៊ីសេរី។ មតិនឹងមិនអាចមើលឃើញភ្លាមៗទេ។ វិទ្យុអាស៊ីសេរី មិនទទួលខុសត្រូវចំពោះខ្លឹមសារនៃមតិដែលបានចុះផ្សាយឡើយ។ សូមគោរពមតិរបស់អ្នកដទៃ ហើយប្រកាន់ខ្ជាប់នូវការពិត។ ឈ្មោះ៖ មតិយោបល់៖   ជ្រើសរើស CAPTCHA៖ Navigation អង្គការ\u200B៤\u200Bគ្រោង\u200Bធ្វើ\u200Bសិក្ខាសាលា\u200Bស្ដី\u200Bអំពី\u200Bហិរញ្ញវត្ថុ\u200Bនយោបាយ ប្រធានបទ បទវិភាគ នយោបាយ សិទ្ធិ\u200Bមនុស្ស សង្គម សេដ្ឋកិច្ច ដីធ្លី បរិស្ថាន សុខភាព ប្រវត្តិសាស្ត្រ ច្បាប់ ខ្មែរ\u200Bក្រហម រូបភាព វីដេអូ វេទិកា\u200Bអ្នក\u200Bស្ដាប់ នាទី\u200Bប្រចាំ\u200Bសប្ដាហ៍ អំពី RFA ភាសា ស្តាប់ អំពីកម្មវិធីរបស់យើង កាលវិភាគ និងរលកធាតុអាកាស ស្តាប់កម្មវិធីថ្មីៗ ស្តាប់តាមផ្កាយរណប ស្តាប់តាមផតកាស មើល រូបភាព វីដេអូ YouTube តាមយើង Twitter RSS News Feeds ទទួល\u200Bការ\u200Bជូន\u200Bដំណឹង ផ្ញើ\u200Bព័ត៌មាន\u200Bមក\u200Bយើង Facebook ស្វែងរកយើង តាមទូរស័ព្ទដៃ ឧបករណ៍ប្រឆាំងការត្រួតពិនិត្យអ៊ីនធឺណិត ប័ណ្ណសារ\u200Bព័ត៌មាន ប្លង់បណ្ដាញ ទាក់ទង ទាក់ទង អ៊ីម៉ែល អំពីយើង ក្រមសីលធម៌ ការគ្រប់គ្រង ការងារ ភាពឯកជន គោលការណ៍ប្រើ USAGM វិទ្យុសំឡេងសហរដ្ឋអាមេរិក ជំនួយ គេហទំព័រ\u200Bទាំងមូល";
        String clean = rfaTextCleaner.clean( txt);
        System.out.println("final : \n"+clean);

    }
}