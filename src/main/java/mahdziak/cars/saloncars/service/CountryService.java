package mahdziak.cars.saloncars.service;

import mahdziak.cars.saloncars.Repository.CountryRepository;
import mahdziak.cars.saloncars.dto.request.CountryRequest;
import mahdziak.cars.saloncars.dto.response.CountryResponse;
import mahdziak.cars.saloncars.entity.Country;
import mahdziak.cars.saloncars.exception.WrongInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryService {
    @Autowired
    private CountryRepository countryRepository;


    public CountryResponse save(CountryRequest request) {
        return new CountryResponse(countryRequestToCountry(request, null));
    }

    public CountryResponse update(CountryRequest request, Long id) throws WrongInputException {
        return new CountryResponse(countryRequestToCountry(request, findOne(id)));
    }

    private Country countryRequestToCountry(CountryRequest request, Country country) {
        if (country == null) {
            country = new Country();
        }
        country.setName(request.getName());
        return countryRepository.save(country);
    }

    public void delete(Long id) throws WrongInputException {
        countryRepository.delete(findOne(id));
    }

    public List<CountryResponse> findAll() {
        return countryRepository.findAll().stream()
                .map(CountryResponse::new)
                .collect(Collectors.toList());
    }

    public Country findOne(Long id) throws WrongInputException {
        return countryRepository.findById(id)
                .orElseThrow(() -> new WrongInputException("Country with id " + id + " not exists"));
    }
}
