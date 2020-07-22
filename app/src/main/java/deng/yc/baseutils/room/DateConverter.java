package deng.yc.baseutils.room;

import androidx.room.TypeConverter;

import java.util.Date;
//@TypeConverters这个方法写在Database那就是全局应用,写在Entity就只应用在本表中.也是根据返回值和参数来规定什么时候使用.
public class DateConverter {
    @TypeConverter
    public Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
