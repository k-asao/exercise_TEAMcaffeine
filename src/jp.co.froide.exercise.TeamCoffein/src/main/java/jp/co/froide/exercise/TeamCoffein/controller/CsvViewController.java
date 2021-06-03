package jp.co.froide.exercise.TeamCoffein.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import jp.co.froide.exercise.TeamCoffein.csv.CsvView;
import jp.co.froide.exercise.TeamCoffein.dao.RemovedEmployeeDao;
import jp.co.froide.exercise.TeamCoffein.dao.UserDao;
import jp.co.froide.exercise.TeamCoffein.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CsvViewController {
    @Autowired
    UserDao userDao;

    @Autowired
    RemovedEmployeeDao removedEmployeeDao;

    @GetMapping(value = "/*.csv", params = "download_file",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE + "; charset=UTF-8; Content-Disposition: attachment")
    @ResponseBody
    public Object index(@RequestParam("download_file") String value) throws JsonProcessingException {

        List<Employee> list;
        switch (value) {
            case "removedList":
                list = removedEmployeeDao.selectRemovedDataAll();
                break;
            default:
                list = userDao.selectEmpAll();
                break;
        }

        List<CsvView> csvs = list.stream().map(
                e -> new CsvView(e.getEmp_id(), e.getName(), e.getKana(), e.getHire_date(), e.getPost_name(), e.getDept_name(), e.getTel(), e.getEmail())
        ).collect(Collectors.toList());
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(CsvView.class).withHeader();
        return mapper.writer(schema).writeValueAsString(csvs);
    }
}
