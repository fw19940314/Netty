namespace java com.elisland.thrift
namespace py py.thrift.generated
#定义类型
typedef i8 short
typedef i16 int
typedef i32 long
typedef bool boolean
typedef string String

#struct 构造类
struct Person {
  1: optional int age,
  2: optional String name,
  3: optional boolean isStudent
}

#异常
exception DataException{
   1:optional String message,
   2:optional String callback,
   3:optional String data
}

# service (interface)
service PersonService{
   Person getPersonByName(1:required String name) throws (1:DataException dataException),
   void savePerson(1:required Person person) throws (1:DataException dataException)
}