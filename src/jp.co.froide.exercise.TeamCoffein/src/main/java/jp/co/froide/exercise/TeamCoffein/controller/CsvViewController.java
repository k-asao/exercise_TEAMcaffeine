package jp.co.froide.exercise.TeamCoffein.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import jp.co.froide.exercise.TeamCoffein.csv.CsvView;
import jp.co.froide.exercise.TeamCoffein.entity.Employee;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CsvViewController {
    public Object csvCreator(Collection<Employee> list) throws JsonProcessingException {
        List<CsvView> csvs = list.stream().map(
                e -> new CsvView(e.getEmp_id(), e.getName(), e.getKana(), e.getHire_date(), e.getPost_name(), e.getDept_name(), e.getTel(), e.getEmail())
        ).collect(Collectors.toList());
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(CsvView.class).withHeader();
        return mapper.writer(schema).writeValueAsString(csvs);
    }
}
