package dany.springframerwork.spring5recipeapp.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by bautisj on 2/1/2018.
 */
@Slf4j
@Service
public class ImageServiceImpl implements ImageService{

    @Override
    public void saveImageFile(Long recipeId, MultipartFile file) {
        log.debug("Received File");
    }
}
