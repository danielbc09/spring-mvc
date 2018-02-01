package dany.springframerwork.spring5recipeapp.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by bautisj on 1/31/2018.
 */
@Service
public interface ImageService {

    void saveImageFile(Long recipeId, MultipartFile file);
}
