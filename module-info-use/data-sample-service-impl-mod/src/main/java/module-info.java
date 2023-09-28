//provide version
module io.github.kandefromparis.khool.jpms.sample.service.mem {
    requires transitive io.github.kandefromparis.khool.jpms.sample.service;
    provides io.github.kandefromparis.khool.jpms.sample.service.DataService
      with     io.github.kandefromparis.khool.jpms.sample.service.mem.DataMemService;
}
